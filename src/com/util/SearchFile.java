package com.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.bean.IFile;

public class SearchFile {
	private static int COUNT = 1;
	private final String ERROR_INFO = "请输入硬盘符,以便查询!";
	private final int SIZE_SEARCH = 20;
	public List<String> searchByName(IFile searchFile){
		
		List<String> fileList = new ArrayList<String>();
		
		System.out.println("==================\n搜索次数:"+(COUNT++)+"\t搜索上限:"+SIZE_SEARCH);
		if(COUNT>=SIZE_SEARCH){
			fileList.add("<center><p style='color:red;font-size:30px;'>搜索上限已到!</p></center>");
			return fileList;
		}
		
		
		String diskname = searchFile.getDiskname();
		String foldername = searchFile.getFoldername();
		String filename = searchFile.getFilename();
		String suffix = searchFile.getSuffix();

		//过滤条件
		
		if(diskname!=null && !"".equals(diskname.trim())){
			
			//盘符和文件夹都在盘符选项中,文件夹选项空
			if(diskname.indexOf(':')!=-1){
				
				if(foldername==null || foldername.trim().equals("")){
					diskname = diskname.replace("/", "\\");
					String allFolderInfo = diskname;
					diskname = getDisknameM(diskname);
					foldername = getFoldernameM(allFolderInfo);
				}
			}
			if(diskname.indexOf(":")==-1 && diskname.length()>1){
				fileList.add(ERROR_INFO);
				return fileList;
			}
			diskname = getDisknameM(diskname);
			
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
					foldername = getFoldernameM(foldername);
					diskname = getDisknameM(allFoldername);
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
				searchIgnoreFilename(dir, contains, fileList);
			}else{
System.out.println("2 文件名有值,后缀有值\t"+filename+","+suffix);				
				//文件名+文件后缀
				FilenameSuffixFilter nameSuffixFilter = new FilenameSuffixFilter(filename, suffix);
				searchIngoreNameWithSuffix(dir, nameSuffixFilter, fileList);
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
				accpetSuffix(dir, filter, fileList);
			}
		}
		//文件名为空
		else if(filename==null || filename.trim().equals("")){
System.out.println("4 文件名为空,后缀为空");
			searchFolderFile(dir, fileList);
		}
		
		return fileList;
	}
	
	
	/*
	 * 查询文件名及后缀
	 */
	private void searchIngoreNameWithSuffix(File dir,
			FilenameSuffixFilter nameSuffixFilter, List<String> fileList) {
		myFilter(dir, nameSuffixFilter, fileList);
	}

	/*
	 * 模糊查询文件名
	 */
	public void searchIgnoreFilename(File dir, ContainsWordFilter filter, List<String> fileLists) {
		myFilter(dir, filter, fileLists);
	}
	
	/*
	 * 搜索该目录下包含此后缀名的文件
	 */
	public void accpetSuffix(File dir, MySuffixFilter filter, List<String> fileLists){
		myFilter(dir, filter, fileLists);
	}
	
	
	//fatherFilter=====================
	public void myFilter(File dir, FilenameFilter filter, List<String> lists){
		File[] files = dir.listFiles();
		
		if(files!=null){
			for(File f:files){
				if(f.isDirectory()){
					myFilter(f, filter, lists);
				}else{
					if(filter.accept(f, f.getName())){
						lists.add(f.getAbsolutePath());
					}
				}
			}
		}
	}




	/*
	 * 搜索该目录下的所有文件
	 */
	private void searchFolderFile(File dir, List<String> fileList) {
		File[] files = dir.listFiles();
		
		if(files!=null){
			for(File f:files){
				if(f.isDirectory()){
					searchFolderFile(f, fileList);
				}else{
					fileList.add(f.getAbsolutePath());
				}
			}
		}
	}
	
	
	/*
	 * 写入到txt
	 */
	public void write2File(List<String> list,File file) throws IOException{
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			bw.write(it.next());
			bw.newLine();
			bw.flush();
		}
		bw.close();
			
	}
	
	//截取盘符
	public String getDisknameM(String anyname){
		String disknameTemp = anyname;
		int end = anyname.indexOf(":");
		if(end!=-1)
			disknameTemp = anyname.substring(0, end);
		return disknameTemp;
	}
	//截取文件夹名称
	public String getFoldernameM(String anyname){
		String foldernameTemp = anyname;
		int start = anyname.indexOf("\\");
		foldernameTemp = anyname.substring(start+1);
		return foldernameTemp;
	}
	
	public static void main(String[] args) {
		String path = "D:/帮助文档";
		System.out.println(path.replace("/", "\\"));
	}
	
}
