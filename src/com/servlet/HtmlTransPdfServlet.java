package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class HtmlTransPdfServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String htmlUrl = request.getParameter("htmlUrl");
		String pdfPath = request.getParameter("pdfPath");
		
		
		if(htmlUrl==null || htmlUrl.trim().equals("")){
			return;
		}
		FileService fs = new FileService();
		//html to local html
		boolean flag = fs.htmlURLTransLocal(htmlUrl, pdfPath);
		request.setAttribute("flag", flag);
		request.setAttribute("localtion", pdfPath);
		request.getRequestDispatcher("/WEB-INF/jsp/html2Local/html2Local.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
