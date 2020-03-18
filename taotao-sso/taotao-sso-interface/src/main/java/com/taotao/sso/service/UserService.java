package com.taotao.sso.service;

import com.taotao.manager.pojo.User;

public interface UserService {

	Boolean check(String param, Integer type);

	User queryUserByTicket(String ticket);
	
	void doRegister(User user);

	String doLogin(User user) ;
}
