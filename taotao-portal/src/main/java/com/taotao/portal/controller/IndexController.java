package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manager.service.ContentService;

@Controller
@RequestMapping("index")
public class IndexController {
	
	@Value("${TAOTAO_AD_ID}")
	private Long TAOTAO_AD_ID;
	@Autowired
	private ContentService contentService;
	
	
	/**
	 * 跳转首页
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String toIndex(Model model){
		String ad = contentService.queryAD(TAOTAO_AD_ID);
		model.addAttribute("AD", ad);
		return "index";
	}
}
