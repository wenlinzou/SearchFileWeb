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
	
	
	private static void renameFile(File file) {
		File [] files = file.listFiles(new MySuffixFilter(".rar"));
		int count=0;
		for (File f : files) {
			if(f.isFile()){
				if(!f.getName().contains("("))
					continue;
				count++;
				StringBuilder sb = new StringBuilder();
//				sb.append(String.valueOf(count));
				String filename = f.getName();
				int start = filename.indexOf('(');
				int end = filename.lastIndexOf(')')+1;
//				System.out.println("start:"+start+"\tend:"+end);
				System.out.println("beforename:"+filename);
				sb.append(filename.substring(0, start));
				sb.append(filename.substring(end));
				filename = sb.toString();
				System.out.println("aftername:"+filename);
				boolean isrename = f.renameTo(new File(f.getParent(),filename));
				System.out.println(isrename);
			}
		}
	}
	//private final int SIZE_SEARCH = 20;
//	public List<String> searchByName(IFile searchFile){
		
	//	List<String> fileList = new ArrayList<String>();
		
//		System.out.println("==================\n搜索次数:"+(COUNT++)+"\t搜索上限:"+SIZE_SEARCH);
//		if(COUNT>=SIZE_SEARCH){
//			fileList.add("<center><p style='color:red;font-size:30px;'>搜索上限已到!</p></center>");
//			return fileList;
//		}
		
		
		/*String diskname = searchFile.getDiskname();
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
		}*/
		
	//	return fileList;
	//}
}
