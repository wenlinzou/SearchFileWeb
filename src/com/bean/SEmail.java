package com.bean;

public class SEmail {
/*
 * email.setHostName("smtp.qq.com");
		email.setAuthentication("lmylay@qq.com", "Daoxiji2012qq");
		email.setCharset("UTF-8");
		try {
			email.addTo("wenlinzou@sina.com");
			email.setFrom("lmylay@qq.com");//必须和Authentication使用的用户相同，否则失败
			email.setSubject("haha");
			email.setMsg("---------------------测试");
 */
	private String username;
	private String password;
	private String hostname;
	private String sendToEmailName;
	private String sendFromEmailName;
	private String subject;
	private String content;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getSendToEmailName() {
		return sendToEmailName;
	}
	public void setSendToEmailName(String sendToEmailName) {
		this.sendToEmailName = sendToEmailName;
	}
	public String getSendFromEmailName() {
		return sendFromEmailName;
	}
	public void setSendFromEmailName(String sendFromEmailName) {
		this.sendFromEmailName = sendFromEmailName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
