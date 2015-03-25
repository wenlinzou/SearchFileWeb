package com.servlet;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.service.LoginService;

public class HtmlWServlet extends HttpServlet{
	private static final int HEIGHT = 30;
	private static final int WIDTH = 120;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		
		
		LoginService ls = new LoginService();
		String tempCode = ls.getHtmlWord(g);

System.out.println("HtmlWordServlet:"+tempCode);

		// 5图形写入浏览器
		response.setContentType("image/jpeg");
		OutputStream os=response.getOutputStream();
		ImageIO.write(image, "jpg", os);
		
		//获得session，并把字符串保存在session中，为后面的对比做基础
		HttpSession session = request.getSession();
		session.setAttribute("wordcheck", tempCode);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
