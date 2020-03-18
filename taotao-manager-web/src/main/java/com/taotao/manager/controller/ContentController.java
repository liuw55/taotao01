package com.taotao.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;

@Controller
@RequestMapping("content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void saveContent(Content content){
		contentService.save(content);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public TaoResult<Content> queryContentByPage(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "20") Integer rows, Long categoryId){
		return contentService.queryContentByPage(page, rows, categoryId);
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public TaoResult<Content> editContent(Content content){
		TaoResult<Content> taoResult = new TaoResult<>();
		try {
			contentService.updateByIdSelective(content);
			taoResult.setStatus("200");
		} catch (Exception e) {
			taoResult.setStatus("500");
			e.printStackTrace();
		}
		 return taoResult;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public TaoResult<Content> editContent(String ids){
		TaoResult<Content> taoResult = new TaoResult<>();
		try {
			String[] split = ids.split(",");
			for (String id : split) {
				contentService.deleteById(Long.parseLong(id));
			}
			taoResult.setStatus("200");
		} catch (NumberFormatException e) {
			taoResult.setStatus("500");
			e.printStackTrace();
		}
		return taoResult;
	}
}
