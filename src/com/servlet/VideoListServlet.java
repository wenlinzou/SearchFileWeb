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
		String filetype = request.getParameter("filetype");
System.out.println("filetype "+filetype);		
		File f = new File(path);
		File[] files = f.listFiles();
		String type = "";
		List<FileI> lists = new ArrayList<FileI>();
		if(files.length>0){
			for (int i = 0; i < files.length; i++) {
				FileI ifile = new FileI();
				String filename = files[i].getName();
				int index = filename.lastIndexOf(".");
				String suffix = "";
				if(index!=-1)
					suffix = filename.substring((index+1));
				if(suffix.equals(filetype)){
					ifile.setFilename(filename);
					lists.add(ifile);
				}
				
/*System.out.println(filename);				
				if(suffix.equals("mp4")){
					
					type = "video";
					System.out.println("this is mp4");
				}
				if(suffix.equals("mp3")){
					ifile.setFilename(filename);
					lists.add(ifile);
					type = "audio";
					System.out.println("this is mp3");
				}*/

			}
		}
		
		request.setAttribute("videolist", lists);
		request.setAttribute("filetype", filetype.equals("mp3")?"audio":"video");
		System.out.println(path);
		
		request.getRequestDispatcher("/WEB-INF/jsp/videolist.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}