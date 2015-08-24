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
import com.service.UploadFileService;

public class VideoListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getServletContext().getRealPath("/resource/upload");
		String filetype = request.getParameter("filetype");
		UploadFileService uploadService = new UploadFileService();
		List<String> suffixLists = new ArrayList<String>();
		
		if(null == filetype || "".equals(filetype)){
			request.getRequestDispatcher("/WEB-INF/notfound.html").forward(request, response);
			return;
		}
		
System.out.println("filetype "+filetype);		
		File f = new File(path);
		File[] files = f.listFiles();
		List<FileI> lists = new ArrayList<FileI>();
		if(files.length>0){
			for (int i = 0; i < files.length; i++) {
				if(files[i].isFile()){
					FileI ifile = new FileI();
					String arrfilename = files[i].getName();
					int index = arrfilename.lastIndexOf(".");
					
					String suffix = "";
					if(index!=-1)
						suffix = arrfilename.substring((index+1));
						suffixLists.add(suffix);
					if(suffix.equals(filetype)){
						String beforeName = uploadService.getUploadBeforeFilename(arrfilename.substring(0, index));
System.out.println("beforename: "+beforeName);						
						ifile.setFilename(beforeName);
						ifile.setArrfilename(arrfilename);
						lists.add(ifile);
					}
				}
				
			}
		}
		//check upload folder exist file suffix
		boolean hasFiletype = true;
		int noSuffix = 0;
		for (int i = 0; i < suffixLists.size(); i++) {
			if(!filetype.equals(suffixLists.get(i))){
				noSuffix++;
			}
			if(noSuffix == suffixLists.size()){
				hasFiletype = false;
			}
		}
		
		if(!hasFiletype){
			try {
				request.getRequestDispatcher("/notfound.html").forward(request,	response);
//				response.sendRedirect("notfound.html");
				
				return;
			} catch (Exception e) {
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
