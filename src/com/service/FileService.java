package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bean.IFile;
import com.util.ContainsWordFilter;
import com.util.FilenameSuffixFilter;
import com.util.MySuffixFilter;
import com.util.SeviceFile;

public class FileService {
	private static int COUNT = 1;
	private final String ERROR_INFO = "请输入硬盘符,以便查询!";
	private final int SIZE_SEARCH = 22;
	
	private SeviceFile sf = new SeviceFile();
	
	public List<String> queryFileLists(IFile iFile){
		List<String> fileList = new ArrayList<String>();
		System.out.println("==================\n搜索次数:"+(COUNT++)+"\t搜索上限:"+SIZE_SEARCH);
		if(COUNT>=SIZE_SEARCH){
			fileList.add("<center><p style='color:red;font-size:30px;'>搜索上限已到!</p></center>");
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
	
	public void write2File(List<String> list,File file){
		try {
			if(file.exists()){
				file.mkdirs();
			}
			sf.write2File(list, file);
		} catch (IOException e) {
			
			System.out.println("MLGB - 找不到"+file);
			e.printStackTrace();
		}
	}
	
	public void renameFile(List<String> lists,String rename){
		sf.renameFile(lists, rename);
	}
}
