package com.taotao.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.mapper.Mapper;
import com.taotao.manager.pojo.User;
import com.taotao.sso.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 检查数据是否可用
	 */
	@RequestMapping(value = "check/{param}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> check(String callback, @PathVariable("param") String param,
			@PathVariable("type") Integer type) {
		// 如果type类型不在1.2.3范围内,则返回参数无效代码
		if (type < 1 || type > 3) {
			// 返回400编码
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}

		try {
			Boolean b = userService.check(param, type);
			String resultStr = "";
			// 如果传入callback参数,说明要使用jsonp
			if (StringUtils.isNotBlank(callback)) {
				// 修改返回值支持jsonp,接受callback伪装为js数据
				resultStr = callback + "(" + b + ")";
			} else {
				resultStr += b;
			}

			return ResponseEntity.ok(resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	/**
	 * 通过ticket查询用户信息
	 */
	@RequestMapping(value = "{ticket}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> queryUserByTicket(@PathVariable("ticket") String ticket, String callback) {
		try {
			User user = userService.queryUserByTicket(ticket);
			if (user != null) {
				String resultStr = "";
				if (StringUtils.isNotBlank(callback)) {
					resultStr = callback + "(" + MAPPER.writeValueAsString(user) + ")";
				}
				return ResponseEntity.ok(resultStr);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

}
