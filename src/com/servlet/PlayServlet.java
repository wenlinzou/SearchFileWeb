package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PlayServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String myUrl = request.getParameter("myUrl");
		
		byte [] bs = myUrl.getBytes("ISO8859-1");
		myUrl = new String(bs,"UTF-8");
		
		int index = myUrl.indexOf(".");
		
		myUrl = myUrl.replace("\\", "/");
		
		String first = myUrl.substring(0, 1).toUpperCase();
		String rest = myUrl.substring(1, myUrl.length());
		String newStr = new StringBuffer(first).append(rest).toString();
		
		//myUrl= "file:///"+newStr;
		
System.out.println("myUrl:"+myUrl);	
//myUrl = "//f:/w/d"; 
		request.setAttribute("playUrl", myUrl);
		String suffixStr = myUrl;
		int endIndex = suffixStr.lastIndexOf(".");
		//.properties
		suffixStr = suffixStr.substring(endIndex);
		
		HttpSession sessionPlay = request.getSession();
		sessionPlay.setAttribute("iPlay", myUrl);
		request.setAttribute("suffixStr", suffixStr);
		request.getRequestDispatcher("/WEB-INF/jsp/play.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
