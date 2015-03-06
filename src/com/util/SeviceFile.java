package com.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.bean.IFile;

public class SeviceFile {
	
	/*
	 * 查询文件名及后缀
	 */
	public void searchIngoreNameWithSuffix(File dir,
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
	public void searchFolderFile(File dir, List<String> fileList) {
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
	public void write2File(List<String> list,File inputFile) throws IOException{
//		List<String> listTemp = new ArrayList<String>();
//		listTemp = lineNum(list, inputFile);
//		list = lineNum(list, inputFile);

		BufferedWriter bw = new BufferedWriter(new FileWriter(inputFile,true));
		int i = 1;
		for (Iterator<String> it = list.iterator(); it.hasNext();i++) {
			bw.write(i+": "+it.next()+"\t"+getCurrentDate());
			bw.newLine();
			bw.flush();
		}
		bw.close();
			
	}
	
	//打印数据带行号 暂未使用
	public List<String> lineNum(List<String> list, File fileLine) throws IOException{
		LineNumberReader liner = new LineNumberReader(new FileReader(fileLine));
		String line = null;
		while((line = liner.readLine())!=null){
			list.add(liner.getLineNumber()+"\t"+line);
		}
		liner.close();
		return list;
	}
	
	//获取当当前系统时间
	public String getCurrentDate(){
		Date dt=new Date();//如果不需要格式,可直接用dt,dt就是当前系统时间
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
		String nowTime="";
		nowTime= df.format(dt);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示
		return nowTime;
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

	
	
	public boolean renameFile(List<String> lists, String sameName) {
		File[] tempFiles = new File[lists.size()];
		boolean canRename = false;
		for (int i = 0; i < lists.size(); i++) {
			tempFiles[i] = new File(lists.get(i));
			String filename = tempFiles[i].getName();
			String oldname = filename;
			String newName = "";
			if(filename.startsWith(sameName)){
				newName = filename.substring(sameName.length());
			
				boolean rename = tempFiles[i].renameTo(new File(tempFiles[i].getParent(),newName));
System.out.println("ISRename:"+rename+"\toldname:"+oldname+"\tsameName:"+sameName+"\tnewName:"+newName);
				canRename = true;
			}else{
				System.out.println("文件名:" + oldname + " -- 前缀不符合:" + sameName);
				canRename = false;
			}
		}
		return canRename;

	}
}
