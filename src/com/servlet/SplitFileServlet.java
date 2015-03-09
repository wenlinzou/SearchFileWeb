package com.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class SplitFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String fileSplitPath = request.getParameter("splitFilePath");
		String sizeStr = request.getParameter("size");
		String suffixname = request.getParameter("suffixname");
		String putPath = request.getParameter("dir");
		
		int size = Integer.parseInt(sizeStr);
		
System.out.println("filesplitPath:"+fileSplitPath);		
		FileService fs = new FileService();
		
		FileInputStream fis = new FileInputStream(fileSplitPath) ;
		if((size*1000) > fis.available()){
			request.setAttribute("boundsSize", "输入分解大小超过文件大小!");
			return;
		}
		
System.out.println(fis.available()/1024);
		
		fs.splitFile(new File(fileSplitPath), new File(putPath), size, suffixname);
		request.setAttribute("localtion", putPath);
		request.getRequestDispatcher("/WEB-INF/jsp/splitSuccess.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
