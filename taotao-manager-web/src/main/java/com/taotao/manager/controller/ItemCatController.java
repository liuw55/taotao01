package com.taotao.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;

@Controller
@RequestMapping("item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;

	// http://127.0.0.1:8081/rest/item/cat/query/1?rows=2
	/**
	 * 分页查询商品类目
	 */
	@RequestMapping("query/{page}")
	@ResponseBody
	public List<ItemCat> queryItemCatByPage(@PathVariable("page") Integer page, @RequestParam("rows") Integer rows) {
		return itemCatService.queryListByPage(page, rows);
	}
	
	/**
	 * 根据parentId查询类目
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ItemCat> queryItemCatByParentId(@RequestParam(value = "id", defaultValue = "0") Long parentId){
		return itemCatService.queryItemCatByParentId(parentId);
	}
}
