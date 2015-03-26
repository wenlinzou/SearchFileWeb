package com.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.bean.IUser;
import com.bean.IWordCheckStyle;
import com.util.MD5Utils;
import com.util.TWordCheck;
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
	
	//html word check
	public String getHtmlWord(Graphics g){
		return wc.wordHtmlChecked(g);
	}
	
	//通过添一个 验证码的一个bean
	public String getTWordCheck(IWordCheckStyle iword){
		return new TWordCheck(iword).getWordSetCheckStyle();
	}
//	public boolean checkWord(String tempCode){
//		
//	}
	
}
