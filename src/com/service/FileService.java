package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.profiler.SpacePadder;

import com.bean.FileI;
import com.util.ITextPdf;
import com.util.FileUtils;
import com.util.SpiderURLUtils;
import com.util.filter.ContainsWordFilter;
import com.util.filter.FilenameSuffixFilter;
import com.util.filter.MySuffixFilter;

public class FileService {
	private static int COUNT = 0;
	private final String FULL = "搜索上限已到!";
	private final String ERROR_INFO = "请输入硬盘符,以便查询!";
	private final int SIZE_SEARCH = 20;
	
	private FileUtils sf = new FileUtils();
	private ITextPdf itd = new ITextPdf();
	
	public List<String> queryFileLists(FileI iFile){
		List<String> fileList = new ArrayList<String>();
		System.out.println("==================\n搜索次数:"+(++COUNT)+"\t搜索上限:"+SIZE_SEARCH);
		if(COUNT>SIZE_SEARCH){
			//<center><p style='color:red;font-size:30px;'>!</p></center>
			fileList.add(FULL);
			return fileList;
		}
		
		String diskname = iFile.getDiskname();
		String foldername = iFile.getFoldername();
		String filename = iFile.getFilename();
		String suffix = iFile.getSuffix();

		//过滤条件
		
		if(diskname!=null && !"".equals(diskname.trim())){
			
			//盘符和文件夹都在盘符选项中,文件夹选项空
			if(diskname.indexOf(':')!=-1){
				
				if(foldername==null || foldername.trim().equals("")){
					diskname = diskname.replace("/", "\\");
					String allFolderInfo = diskname;
					diskname = sf.getDisknameM(diskname);
					foldername = sf.getFoldernameM(allFolderInfo);
				}
			}
			if(diskname.indexOf(":")==-1 && diskname.length()>1){
				fileList.add(ERROR_INFO);
				return fileList;
			}
			diskname = sf.getDisknameM(diskname);
			
		}else{
			if(foldername==null || foldername.trim().equals("")){
				fileList.add(ERROR_INFO);
				return fileList;
				
			}
			//盘符位置空,但文件夹名称包含了盘符名称	
			else{
				if(foldername.indexOf(':')!=-1){
					foldername = foldername.replace("/", "\\");
					String allFoldername = foldername;
					foldername = sf.getFoldernameM(foldername);
					diskname = sf.getDisknameM(allFoldername);
				}else{
					//文件夹名称内没有盘符信息
					fileList.add(ERROR_INFO);
					return fileList;
				}
			}
		}
		diskname+=":/";
		
		File dir = new File(diskname, foldername);
System.out.println("dir:\t"+dir);		

		//文件名不为空
		if((null!=filename && !filename.trim().equals(""))){
			//后缀名为空
			if(suffix==null || suffix.trim().equals("")) {
System.out.println("1 文件名有值,后缀为空\t"+filename);				
				ContainsWordFilter contains = new ContainsWordFilter(filename);
				sf.searchIgnoreFilename(dir, contains, fileList);
			}else{
System.out.println("2 文件名有值,后缀有值\t"+filename+","+suffix);				
				//文件名+文件后缀
				FilenameSuffixFilter nameSuffixFilter = new FilenameSuffixFilter(filename, suffix);
				sf.searchIngoreNameWithSuffix(dir, nameSuffixFilter, fileList);
			}
		}
		
		//后缀名
		if(null!=suffix && !suffix.trim().equals("")){
			char[] temps = suffix.toCharArray();
			if(temps[0]!='.')
				suffix = "."+suffix;
			
			//文件名不为空
			if(filename==null || filename.trim().equals("")){
System.out.println("3 文件名为空,后缀有值"+"\t"+suffix);
				MySuffixFilter filter = new MySuffixFilter(suffix);
				sf.accpetSuffix(dir, filter, fileList);
			}
		}
		//文件名为空
		else if(filename==null || filename.trim().equals("")){
System.out.println("4 文件名为空,后缀为空");
			sf.searchFolderFile(dir, fileList);
		}
		return fileList;
	}
	
	/**
	 * 将list<String>写入到文件中
	 * @param list
	 * @param file
	 * @return
	 */
	public boolean write2File(List<String> list,File file){
		boolean flag = false;
		try {
			if(file.exists()){
				file.mkdirs();
			}
			sf.write2File(list, file);
			flag = true;
		} catch (IOException e) {
			System.out.println("MLGB - 找不到"+file);
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 重命名文件
	 * @param lists
	 * @param rename
	 * @return
	 */
	public boolean renameFile(List<String> lists,String rename){
		return sf.renameFile(lists, rename);
	}
	
	/**
	 * 分解文件
	 * @param srcFile
	 * @param destDir
	 * @param split_size
	 * @param suffix
	 * @return
	 */
	public boolean splitFile(File srcFile, File destDir,int split_size, String suffix){
		boolean flag = false;
		int indexStart = suffix.indexOf(".");
		if(indexStart<0)
			suffix = "." + suffix;
		
		try {
System.out.println("size:"+srcFile);			
				
			sf.splitFile(srcFile, destDir, split_size, suffix);
			flag = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 合并文件
	 * @param mergreFile
	 * @return
	 */
	public boolean mergeFile(File mergreFile){
		boolean flag = false;
		try {
			String filePath = mergreFile.getAbsolutePath();
			String fileInPath = filePath.replace("\\", "/");
			int pathEnd =fileInPath.lastIndexOf("/");
System.out.println("SERVICE-MergeFIle:"+fileInPath);			
			fileInPath = filePath.substring(0, pathEnd);
			
			int suffixStart = filePath.lastIndexOf(".");
			String suffixName = filePath.substring(suffixStart);
			if(!suffixName.equals(".properties")){
//				throw new RuntimeException("文件后缀名不是"+".properties");
				System.out.println("文件后缀名不是"+".properties");
			}
			File srcFile = new File(fileInPath);
System.out.println("SERVICE:fileInPath:"+fileInPath+"\tsuffixname:"+suffixName);	

			sf.mergeFile(srcFile,suffixName);
			flag = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return flag;
	}

	
	public void htmlTransPdf(String url, String filePath){
		try {
			//得到url的文件数据流
			List<String> htmldata = new SpiderURLUtils().getDataByURL(url);
			//将数据写到本地
			String htmlUrl = url;
			String pdfPath = filePath;
			
			int startHtml = htmlUrl.lastIndexOf("/");	
			int endHtml = htmlUrl.lastIndexOf(".");
			String htmlName = "";
			if(endHtml<startHtml){
				if(startHtml>0){
					String temphtmlUrl = htmlUrl.substring(0, htmlUrl.length()-1);
					htmlName = temphtmlUrl.substring(temphtmlUrl.lastIndexOf("/")+1);
System.out.println("htmlUrl:"+htmlUrl+"\thtmlname:"+htmlName);					
				}
			}else {
				htmlName = htmlUrl.substring(startHtml+1, endHtml);
			}
			
			String htmlsuffix = ".html";
System.out.println("htmlsuffix:"+htmlsuffix);		

			String inputTempFile = filePath+"/"+htmlName+htmlsuffix;
			inputTempFile = sf.changeIngellUrlName(inputTempFile);
			
System.out.println("inputTempFile:"+inputTempFile);			
			sf.write2File(htmldata, inputTempFile);
System.out.println("over!");			
			//读取html文件将其转成pdf
			String pdfsuffix = ".pdf";
			pdfPath = filePath+"/"+htmlName+pdfsuffix;
System.out.println("pdfPath:"+pdfPath);			
//			itd.htmlTransPdf1(inputTempFile, pdfPath);
//			new ITextPdf().htmlTransPdf1(filePath, pdfurl);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * html2pdf 暂时只是爬虫下来 html
	 * @param url
	 * @param filePath
	 */
	public boolean htmlURLTransLocal(String url, String filePath){
		boolean flag = false;
		try {
			//得到url的文件数据流
			List<String> htmldata = new SpiderURLUtils().getDataByURL(url);
			//将数据写到本地
			String htmlUrl = url;
			String pdfPath = filePath;
			
			int startHtml = htmlUrl.lastIndexOf("/");	
			int endHtml = htmlUrl.lastIndexOf(".");
			String htmlName = "";
			if(endHtml<startHtml){
				if(startHtml>0){
					String temphtmlUrl = htmlUrl.substring(0, htmlUrl.length()-1);
					htmlName = temphtmlUrl.substring(temphtmlUrl.lastIndexOf("/")+1);
System.out.println("htmlUrl:"+htmlUrl+"\thtmlname:"+htmlName);					
				}
			}else {
				htmlName = htmlUrl.substring(startHtml+1, endHtml);
			}
			
			String htmlsuffix = ".html";
System.out.println("htmlsuffix:"+htmlsuffix);		

			String inputTempFile = filePath+"/"+htmlName+htmlsuffix;
			inputTempFile = sf.changeIngellUrlName(inputTempFile);
			
System.out.println("inputTempFile:"+inputTempFile);			
			sf.write2File(htmldata, inputTempFile);
			//读取html文件将其转成pdf
			String pdfsuffix = ".pdf";
			pdfPath = filePath+"/"+htmlName+pdfsuffix;
//System.out.println("pdfPath:"+pdfPath);	暂时没有转pdf		
//			itd.htmlTransPdf1(inputTempFile, pdfPath);
//			new ITextPdf().htmlTransPdf1(filePath, pdfurl);
			flag = true;
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
