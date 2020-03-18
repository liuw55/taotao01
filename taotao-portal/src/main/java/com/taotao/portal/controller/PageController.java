package com.taotao.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("page")
public class PageController {
	
	/**
	 * 跳转页面的方法
	 */
	@RequestMapping("{pageName}")
	public String toPage(@PathVariable("pageName") String pageName, Model model, @RequestParam(value = "redirectURL", defaultValue = "") String redirectURL){
		model.addAttribute("redirectURL", redirectURL);
		return pageName;
	}
}
