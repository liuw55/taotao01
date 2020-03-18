package com.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {
	
	/**
	 * 通用页面跳转方法
	 */
	@RequestMapping("{pageName}")
	public String toPage(@PathVariable String pageName){
		return pageName;
	}
}
