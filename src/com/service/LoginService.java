package com.service;

import java.awt.Graphics;

import com.bean.IUser;
import com.util.MD5Utils;
import com.util.WordCheck;

public class LoginService {
	private WordCheck wc = new WordCheck();
	
	public boolean login(IUser user){
		//niubi 1234
		String username,password;
		username = MD5Utils.md5(user.getUsername());
		password = MD5Utils.md5(user.getPassword());
		if("QlZEDFSijbzfyDficOkrmA==".equals(username) &&"gdyb21LQTcIANtvYMT7QVQ==".equals(password)){
			return true;
		}else{
			return false;

		}
	}
	//not use
	public boolean wordCheck(String tempCode,Graphics g){
		String temp = wc.wordChecked(g);
		if(temp.equals(tempCode)){
			return true;
		}else{
			return false;
		}
	}
	public String getWord(Graphics g){
		return wc.wordChecked(g);
	}
//	public boolean checkWord(String tempCode){
//		
//	}
	
}
