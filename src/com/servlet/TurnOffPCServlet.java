package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.PCService;

public class TurnOffPCServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String tempTime = request.getParameter("turnoffTime");
		tempTime = tempTime.trim();
System.out.println("tempTIme:"+tempTime);		
		int time = Integer.parseInt(tempTime);
		
		
		PCService ps = new PCService();
		ps.turnOffPC(time);
		request.setAttribute("turnOffSuccess", "将在"+time+"分钟后关机");
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
