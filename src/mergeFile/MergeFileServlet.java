package mergeFile;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.service.FileService;

public class MergeFileServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String mergeFilePath = request.getParameter("splitFilePath");
System.out.println("servlet:"+mergeFilePath);		
		
		FileService fs = new FileService();
		fs.mergeFile(new File(mergeFilePath));
		request.setAttribute("localtion", mergeFilePath);
		request.getRequestDispatcher("/WEB-INF/jsp/mergeSuccess.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
