package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class GreyPhotoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imgpath = request.getParameter("imgPath");
		String webName = request.getContextPath();
		
		System.out.println("contenapath:"+webName);
        if (imgpath == null) {
            System.out.println("is null");
            return;
        }
        String method = request.getMethod();
        FileService fileService = new FileService();
        //get方式提交
        if ("GET".equals(method)) {
            byte[] bs = imgpath.getBytes("ISO8859-1");
            imgpath = new String(bs, "UTF-8");
            boolean pass = fileService.getUrlPhotoMakeItGrey(imgpath,webName);
            if (pass) {
                request.setAttribute("existPhoto", "1");
                request.getRequestDispatcher("otherInfo/photoGrey.jsp").forward(request, response);
            }
//            PhotoUtils.saveToFile(imgpath);
        }
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
