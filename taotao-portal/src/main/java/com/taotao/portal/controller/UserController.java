package com.taotao.portal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.User;
import com.taotao.portal.util.CookieUtils;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;
	@Value("${TT_TICKET}")
	private String TT_TICKET;

	/**
	 * 用户注册
	 */
	@RequestMapping(value = "doRegister", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doRegister(User user) {
		userService.doRegister(user);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "200");
		return map;
	}

	/**
	 * 用户登陆
	 */
	@RequestMapping(value = "doLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> doLogin(User user, HttpServletRequest request, HttpServletResponse response) {
		String ticket = userService.doLogin(user);
		if (StringUtils.isNotBlank(ticket)) {
			CookieUtils.setCookie(request, response, this.TT_TICKET, ticket, 60 * 60 * 24, true);
			Map<String, Object> map = new HashMap<>();
			map.put("status", "200");
			return map;  
		}
		return null;
	}
}
