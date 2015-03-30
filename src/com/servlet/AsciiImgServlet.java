package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.SimpleAsciiImg;

public class AsciiImgServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String imgpath = request.getParameter("imgpath");
		
		//get方式提交
		byte [] bs = imgpath.getBytes("ISO8859-1");
		imgpath = new String(bs,"UTF-8");
System.out.println("imgpath:"+imgpath);
		
		String img = SimpleAsciiImg._BitmapConvert(imgpath);
		request.setAttribute("img", img);
		request.getRequestDispatcher("/WEB-INF/jsp/asciiImgSuccess.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
