package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class RenameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		FileService fs = new FileService();
		
		String checkedName = request.getParameter("rename");
		String sameName = request.getParameter("samename");
		if(sameName==null || sameName.equals("")){
			response.sendRedirect("search.action");
			return;
		}
		
		String[] temp = checkedName.split(",");
		for (int i = 0; i < temp.length; i++) {
			temp[i] = temp[i].replace("\\", "/");
			
		}
		List<String> lists = new ArrayList<String>();
		lists = Arrays.asList(temp);
		boolean canRename = fs.renameFile(lists, sameName);
		if(canRename){
			request.setAttribute("canRename", "文件名修改成功!");
			request.getRequestDispatcher("/WEB-INF/jsp/canRename.jsp").forward(request, response);
			return;
		}else{
			request.setAttribute("canRename", "没有找到合适的文件名称,或者修改后的名称与其他文件名称冲突");
			request.getRequestDispatcher("/WEB-INF/jsp/cannotRename.jsp").forward(request, response);
			return;
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
