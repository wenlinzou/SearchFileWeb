package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.IUser;
import com.service.LoginService;

public class MD5Servlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		StringBuilder sb = new StringBuilder();
		sb.append("<message>");
System.out.println("username:"+username+"\tpassword:"+password);		
		if(username==null || username.length()==0){
			sb.append("用户名不能为空").append("</message>");
			out.print(sb.toString());
		}else if(password==null || password.length()==0){
			sb.append("密码不能为空").append("</message>");
			out.print(sb.toString());
		}
		else{
			LoginService ls  = new LoginService();
			IUser user = new IUser();
			user.setUsername(username);
			user.setPassword(password);
			boolean isLogin = ls.login(user);
			
			if(isLogin){
				sb.append("canLogin").append("</message>");
				out.print(sb.toString());
//System.out.println(isLogin);
//				request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
				return;
			}else{
				sb.append("用户名或密码不正确").append("</message>");
				out.print(sb.toString());
//				HttpSession session = request.getSession();
//				session.setAttribute("user", user);
//				response.sendRedirect("index.jsp");
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
