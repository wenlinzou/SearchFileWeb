package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.User;
import com.service.impl.UserServiceImpl;

public class LoginMD5Servlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		StringBuilder sb = new StringBuilder();
		sb.append("<message>");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String wordcheck = request.getParameter("wordcheck");

		HttpSession session = request.getSession();
		String	wordTemp = (String) session.getAttribute("wordcheck");
		//转换小写

		boolean wordOK = wordcheck.toLowerCase().equals(wordTemp.toLowerCase());
System.out.println("wordcheck value:"+wordcheck+"\tworktemp:"+wordTemp+"\twordok:"+wordOK);		
		PrintWriter out = response.getWriter();
		
System.out.println("username:"+username+"\tpassword:"+password+"\twordcheck:"+wordcheck);		
		if(username==null || username.length()==0 || "输入用户名".equals(username)){
			sb.append("用户名不能为空").append("</message>");
			out.print(sb.toString());return;
		}else if(password==null || password.length()==0 || "输入密码".equals(password)){
			sb.append("密码不能为空").append("</message>");
			out.print(sb.toString());return;
		}else if(wordcheck==null || wordcheck.length()==0 || "输入验证码".equals(wordcheck)){
			sb.append("验证码不能为空").append("</message>");
			out.print(sb.toString());return;
		}
		else{
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
//			LoginService ls = new LoginService();
//			boolean isLogin = ls.login(user);
			UserServiceImpl us = new UserServiceImpl();
			User u = us.login(username, password);
			if(!wordOK){
				
				sb.append("验证码"+wordcheck+"输入不正确!").append("</message>");
				out.print(sb.toString());return;
			}
			//if(isLogin && wordOK){
			if(u!=null && wordOK){
				sb.append("canLogin").append("</message>");
				session.setAttribute("user", user);
				out.print(sb.toString());return;
			}else{
				sb.append("用户名或密码不正确").append("</message>");
				out.print(sb.toString());return;
			}
		}
		
		  
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
