package com.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.bean.SEmail;

public class SendMail {
	public static boolean sendMail(SEmail semail){
		boolean flag = false;
		SimpleEmail email = new SimpleEmail();
//		email.setHostName("smtp.sina.cn");
		email.setHostName(semail.getHostname());
		email.setAuthentication(semail.getUsername(), semail.getPassword());
		email.setCharset("UTF-8");
		try {
			email.addTo(semail.getSendToEmailName());//发送的对象
			email.setFrom(semail.getSendFromEmailName());//必须和Authentication使用的用户相同，否则失败
			email.setSubject(semail.getSubject());//标题
			email.setMsg(semail.getContent());
			email.send();
			flag = true;
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
