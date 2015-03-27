package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.SEmail;
import com.service.impl.UserServiceImpl;
import com.util.MD5Utils;
import com.util.SeviceFile;

public class SendEmailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sendToEmailName = request.getParameter("sendToEmailName");
		String sendFromEmailName = username;
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		StringBuilder hostname = new StringBuilder();
		hostname.append("smtp");
//		String hostname = "smtp.sina.com";
		//截取
System.out.println("username:"+username+" \npassword:"+MD5Utils.md5(password)+"\n to:"+sendToEmailName+"\n from:"+sendFromEmailName
		+"\n subject:"+subject+"\n content:"+content);		
		String hostat = SeviceFile.getAtName(username);
		hostname.append(".").append(hostat).append(".com");
		
		SEmail semail = new SEmail();
		semail.setUsername(username);
		semail.setPassword(password);
		semail.setHostname(hostname.toString());
		semail.setSendFromEmailName(sendFromEmailName);
		semail.setSendToEmailName(sendToEmailName);
		semail.setSubject(subject);
		semail.setContent(content);
		
		UserServiceImpl us = new UserServiceImpl();
		boolean flag = us.sendEmail(semail);
		if(flag){
			request.setAttribute("message", "发送邮件成功!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}else{
			request.setAttribute("message", "发送邮件失败!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
