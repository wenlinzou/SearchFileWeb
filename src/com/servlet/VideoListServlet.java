package com.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.FileI;

public class VideoListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletContext().getRealPath("/resource/upload");
		File f = new File(path);
		File[] files = f.listFiles();
		List<FileI> lists = new ArrayList<FileI>();
		if(files.length>0){
			for (int i = 0; i < files.length; i++) {
				FileI ifile = new FileI();
				String filename = files[i].getName();
System.out.println(filename);				
				if(filename.endsWith("mp4")){
					ifile.setFilename(filename);
					lists.add(ifile);
				}
			}
		}
		request.setAttribute("videolist", lists);
		System.out.println(path);
		
		request.getRequestDispatcher("/WEB-INF/jsp/videolist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
