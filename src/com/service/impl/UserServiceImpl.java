package com.service.impl;

import com.bean.User;
import com.bean.WordCheckStyle;
import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.exception.UserExistException;
import com.util.MD5Utils;
import com.util.TWordCheck;

public class UserServiceImpl {
	private UserDao dao = new UserDaoImpl();

	//注册
	public void register(User user) throws UserExistException{
		boolean flag = dao.find(user.getUsername());
		if(flag){
			throw new UserExistException();
		}else{
			user.setShowpassword(user.getPassword());
			user.setPassword(MD5Utils.md5(user.getPassword()));
			dao.add(user);
		}
	}
	
	//登陆
	public User login(String username, String password){
		
		password = MD5Utils.md5(password);
		return dao.find(username, password);
	}
	
	//通过添一个 验证码的一个bean
	public String getTWordCheck(WordCheckStyle iword){
		return new TWordCheck(iword).getWordSetCheckStyle();
	}
}