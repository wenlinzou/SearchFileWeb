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

import com.bean.WordCheckStyle;
import com.service.impl.UserServiceImpl;

public class WordCheckJSPServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//iwordcheckstyle bean
		WordCheckStyle iword = new WordCheckStyle();
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
		iword.setWordFont(new Font("黑体",Font.BOLD,23));
		String tempCode = us.getTWordCheck(iword);
		/*int height = 30;
		int width = 120;
		//Graphics g, Color backColor, Color borderColor, Color lineColor,Color fontColor, Font wordFont
		String tempCode = ls.getTWordCheck(height,width,g,Color.WHITE,Color.BLACK,Color.GRAY,new Color(197,0,0),new Font("黑体",Font.BOLD,25)
		);*/
		
		

System.out.println("TTTWordServlet:"+tempCode);

		// 5图形写入浏览器
		response.setContentType("image/jpeg");
		
		//获得session，并把字符串保存在session中，为后面的对比做基础
		HttpSession session = request.getSession();
		session.setAttribute("wordcheck", tempCode);
		
		OutputStream os=response.getOutputStream();
		ImageIO.write(image, "jpg", os);
		
		response.flushBuffer();
		os.close();
		os=null;
		
		
		
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
