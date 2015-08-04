package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.FileI;
import com.bean.Page;
import com.service.FileService;

public class SearchServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String diskname = request.getParameter("diskname");
		String foldername = request.getParameter("foldername");
		String filename = request.getParameter("filename");
		String suffix = request.getParameter("suffix");
		
		FileI iFile = new FileI();
		FileService ss = new FileService();
		List<String> fileLists = new ArrayList<String>();
		
		Page page = new Page();
		String currentPage = request.getParameter("page");
		page.setCurrentPage(Integer.parseInt(currentPage==null||currentPage.equals("")?"1":currentPage));
		page.setPageSize(20);
		
		//当前页码
		int currPage = 0;
		//总页数
		int totalPage = 0;
		//显示条数
		int pageSize = 0;
		//总记录数
		int totalCount = 0;
		
		Object fileObj = session.getAttribute("iFile");
		

		if(null != fileObj){
			System.out.println("ifilet:"+fileObj);
			if(fileObj instanceof FileI){
				FileI f = (FileI)fileObj;
				iFile = f;
				fileLists = ss.queryFileListsPage(new FileI(f.getDiskname(), f.getFoldername(), f.getFilename(), f.getSuffix()), page);
				currPage = page.getCurrentPage();
				totalPage = page.getTotalPage();
				pageSize = page.getPageSize();
				totalCount = page.getTotalCount();
				
			}
		}else{
			
			iFile.setDiskname(diskname);
			iFile.setFilename(filename);
			iFile.setFoldername(foldername);
			iFile.setSuffix(suffix);
		
		
		
		
//		List<String> fileLists = ss.queryFileLists(new FileI(diskname, foldername, filename, suffix));
		fileLists = ss.queryFileListsPage(new FileI(diskname, foldername, filename, suffix), page);
		
		}
		
		//定义是否搜索到内容的标记
		int flag = -1;
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
			flag = 1;//没有内容
		}
		String fullStr = fileLists.get(0).toString();
		if("搜索上限已到!".equals(fullStr)){
			request.setAttribute("fullStr", "<center><p style='color:red;font-size:30px;'>"+fullStr+"!</p></center>");
			request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
			return;
		}
		session.setAttribute("fileLists", fileLists);
		//request.setAttribute("fileLists", fileLists);
		session.setAttribute("iFile", iFile);
		request.setAttribute("page", currPage);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("flag", flag);
		
		request.getRequestDispatcher("/WEB-INF/jsp/searchSuccess1.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
