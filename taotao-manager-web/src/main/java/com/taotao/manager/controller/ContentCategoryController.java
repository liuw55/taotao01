package com.taotao.manager.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ContentCategory> queryContentCategoryByParentId(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		return contentCategoryService.queryContentCategoryByParentId(parentId);
	}
	
	@RequestMapping(value="add",method = RequestMethod.POST)
	@ResponseBody
	public ContentCategory saveContentCategory(ContentCategory contentCategory){
		ContentCategory result = null;
		try {
			result = contentCategoryService.saveContentCategory(contentCategory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public String update(ContentCategory contentCategory){
		String msg = "0";
		try {
			contentCategoryService.updateByIdSelective(contentCategory);
			msg = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String delete(Long parentId,Long id){
		String msg = "0";
		try {
			contentCategoryService.deleteContentCategoryById(parentId, id);
			msg = "1";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
}
