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
		response.setCharacterEncoding("UTF-8");
		
		String imgpath = request.getParameter("imgpath");

		String method = request.getMethod();
System.out.println("method:"+method);
		
		if(imgpath==null || imgpath.trim().equals("")){
			request.setAttribute("message", "图像字符画失败!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}
		//get方式提交
		if("GET".equals(method)){
			byte [] bs = imgpath.getBytes("ISO8859-1");
			imgpath = new String(bs,"UTF-8");
		}
		if(imgpath.endsWith(".jpg") || imgpath.endsWith(".png") || imgpath.endsWith("gif")
				|| imgpath.endsWith(".bmp")){

			String img = SimpleAsciiImg._BitmapConvert(imgpath);
			request.setAttribute("img", img);
			request.getRequestDispatcher("/WEB-INF/jsp/asciiImgSuccess.jsp").forward(request, response);
		}else{
			if(imgpath.endsWith(".jpeg"))
				request.setAttribute("message", "图像字符画失败!暂不支持"+".jpeg");
			else
				request.setAttribute("message", "图像字符画失败!");
			request.getRequestDispatcher("/WEB-INF/jsp/successT.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
