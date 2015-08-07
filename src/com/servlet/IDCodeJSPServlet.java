package com.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.IDCodeStyle;
import com.bean.ImageID;
import com.service.impl.UserServiceImpl;

public class IDCodeJSPServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//iwordcheckstyle bean
		/*IDCodeStyle iword = new IDCodeStyle();
		iword.setHeight(30);
		iword.setWidth(120);
		
		BufferedImage image = new BufferedImage(iword.getWidth(),iword.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		iword.setG(g);
		UserServiceImpl us = new UserServiceImpl();
		iword.setBackColor(Color.WHITE);
		iword.setBorderColor(Color.BLACK);
		iword.setFontColor(new Color(197,0,0));
		iword.setLineColor(Color.GRAY);
		iword.setWordFont(new Font("宋体",Font.BOLD,23));
		String tempCode = us.getTWordCheck(iword);*/
		
		ImageID imageId = new ImageID();
		imageId.setHeight(30);
		imageId.setWidth(120);
		
		BufferedImage buffImage = new BufferedImage(imageId.getWidth(),imageId.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics graphics = buffImage.getGraphics();
		imageId.setG(graphics);
		
		UserServiceImpl us = new UserServiceImpl();
		String tempCode = us.getImageWordCheck(imageId);
		
		

System.out.println("TTTWordServlet:"+tempCode);

		// 5图形写入浏览器
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		//获得session，并把字符串保存在session中，为后面的对比做基础
		HttpSession session = request.getSession();
		session.setAttribute("wordcheck", tempCode);
		
		OutputStream os=response.getOutputStream();
		ImageIO.write(buffImage, "jpg", os);
		
		response.flushBuffer();
		os.close();
		os=null;
		
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
