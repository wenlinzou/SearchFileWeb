package com.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bean.IFile;
import com.util.SearchFile;

public class FileService {
	private SearchFile sf = new SearchFile();
	
	public List<String> queryFileLists(IFile iFile){
		/*StringBuilder sb = new StringBuilder();
		String diskname = iFile.getDiskname();
		String foldername = iFile.getFoldername();
		String filename = iFile.getFilename();
		String suffix = iFile.getSuffix();
		
		if(diskname!=null && !"".equals(diskname.trim())){
			sb.append(diskname).append(":");
		}else{
			//fileList.add("请输入硬盘符,以便查询!");
			//return fileList;
		}
		if(foldername!=null && !"".equals(foldername.trim())){
			sb.append("/").append(foldername);
		}
		if(filename!=null && !"".equals(filename.trim())){
			sb.append("/").append(filename);
		}
		if(suffix!=null && !"".equals(suffix.trim())){
			sb.append(suffix);
		}*/
		
		return sf.searchByName(iFile);
	}
	
	public void write2File(List<String> list,File file){
		try {
			sf.write2File(list, file);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("找不到");
		}
	}
}
