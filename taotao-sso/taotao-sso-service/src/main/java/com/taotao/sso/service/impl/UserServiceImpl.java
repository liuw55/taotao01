package com.taotao.sso.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taotao.manager.mapper.UserMapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.redis.RedisUtils;
import com.taotao.sso.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisUtils redisUtils;
	@Value("${SSO_TICKET_KEY}")
	private String SSO_TICKET_KEY;
	@Value("${SSO_TAOTAO_TICKET_INCR}")
	private String SSO_TAOTAO_TICKET_INCR;
	@Value("${SSO_TAOTAO_TICKET}")
	private String SSO_TAOTAO_TICKET;

	private final ObjectMapper MAPPER = new ObjectMapper();

	public Boolean check(String param, Integer type) {
		User user = new User();
		switch (type) {
		case 1:
			user.setUsername(param);
			break;
		case 2:
			user.setPhone(param);
			break;
		case 3:
			user.setEmail(param);
			break;
		default:
			break;
		}
		int count = userMapper.selectCount(user);
		if (count > 0) {
			return false;
		} else {
			return true;
		}
	}

	public User queryUserByTicket(String ticket) {
		User user = null;
		String jsonStr = redisUtils.get(SSO_TICKET_KEY + ticket);
		if (StringUtils.isNotBlank(jsonStr)) {
			redisUtils.expire(SSO_TICKET_KEY + ticket, 60 * 60);
			try {
				user = MAPPER.readValue(jsonStr, User.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	public void doRegister(User user) {
		user.setCreated(new Date());
		user.setUpdated(user.getCreated());
		userMapper.insert(user);
	}

	public String doLogin(User user) {
		User result = userMapper.selectOne(user);
		if (result != null) {
			try {
				// 生成唯一数ticket,可以使用redis的唯一数+用户id
				String ticket = redisUtils.incr(SSO_TAOTAO_TICKET_INCR) + result.getId() + "";
				// 把ticket和用户数据放到redis中,模拟session,原来session有效时间为半小时
				redisUtils.set(SSO_TICKET_KEY + ticket, MAPPER.writeValueAsString(result), 60 * 30);
				return ticket;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
