package com.bean;

public class IFile {
	private String diskname;
	private String foldername;
	private String filename;
	private String suffix;
	
	
	public IFile() {
		super();
		
	}
	public IFile(String diskname, String foldername, String filename,
			String suffix) {
		super();
		this.diskname = diskname;
		this.foldername = foldername;
		this.filename = filename;
		this.suffix = suffix;
	}
	public String getDiskname() {
		return diskname;
	}
	public void setDiskname(String diskname) {
		this.diskname = diskname;
	}
	public String getFoldername() {
		return foldername;
	}
	public void setFoldername(String foldername) {
		this.foldername = foldername;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
}
