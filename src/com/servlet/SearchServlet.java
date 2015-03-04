package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.IFile;
import com.service.FileService;

public class SearchServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		String diskname = request.getParameter("diskname");
		String foldername = request.getParameter("foldername");
		String filename = request.getParameter("filename");
		String suffix = request.getParameter("suffix");
		
		
		FileService ss = new FileService();
		
		List<String> fileLists = ss.queryFileLists(new IFile(diskname, foldername, filename, suffix));
		HttpSession session = request.getSession();
		
		if(fileLists==null || fileLists.size()<1){
			StringBuilder sb = new StringBuilder();
			sb.append("<center>没能搜索到");
			if(filename!=null && !filename.trim().equals("")){
				sb.append("文件名为:<p style='color:red;font-size:30px;'>").append(filename).append("</p>");
			}
			if(suffix!=null && !suffix.trim().equals("")){
				sb.append("后缀为:<p style='color:green;font-size:30px;'>")
				.append(suffix)
				.append("</p>");
			}
			sb.append("的文件!</center>");
			
			fileLists.add(sb.toString());
		}
		session.setAttribute("fileLists", fileLists);
		//request.setAttribute("fileLists", fileLists);
		
		request.getRequestDispatcher("/WEB-INF/jsp/success1.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
