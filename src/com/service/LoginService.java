package com.service;

import com.bean.IUser;
import com.util.MD5Utils;

public class LoginService {
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
}
