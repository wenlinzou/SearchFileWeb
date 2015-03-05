package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.FileService;

public class PrintServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String filename = request.getParameter("filename");
		String diskname = request.getParameter("diskname");
		
		FileService fs = new FileService();
		
		File iPrintFile= new File(diskname, filename); 
		
		if(filename==null || filename.trim().equals("")){
			response.sendRedirect("input.action");
			return;
		}
		filename+=".txt";
		request.setAttribute("localtion", diskname+":"+filename);
		
		HttpSession session = request.getSession();
		
		List<String> list = (List<String>) session.getAttribute("fileLists");
		
		fs.write2File(list, new File(diskname+":",filename));
		
		request.getRequestDispatcher("/WEB-INF/jsp/writeSuccess.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
