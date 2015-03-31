package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.HtmlToDoc;
import com.util.ServiceFile;

public class HtmlToDocServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String method = request.getMethod();
		String htmlpath = request.getParameter("htmlpath");
		
		if("GET".equals(method)){
			byte [] bs = htmlpath.getBytes("ISO8859-1");
			htmlpath = new String(bs,"UTF-8");
		}
System.out.println("htmltodoc htmlpath:"+htmlpath);		
		//截取路径
		String writepath = htmlpath.substring(0, htmlpath.lastIndexOf("/"));
		
		
		//html to doc
		boolean flag = false;
		try {
			flag = HtmlToDoc.writeDocFile(htmlpath, writepath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			request.setAttribute("message", htmlpath+"转换DOC成功!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}else{
			request.setAttribute("message", htmlpath+"转换DOC失败!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
