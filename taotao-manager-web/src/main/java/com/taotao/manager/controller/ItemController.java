package com.taotao.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;
import com.taotao.manager.pojo.ItemDesc;
import com.taotao.manager.service.ItemDescService;
import com.taotao.manager.service.ItemService;

@Controller
@RequestMapping("item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService itemDescService;

	@RequestMapping
	@ResponseBody
	public String saveItem(Item item, String desc) {
		String msg = "0";
		try {
			itemService.saveItem(item, desc);
		} catch (Exception e) {
			msg = "1";
			e.printStackTrace();
		}
		return msg;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public TaoResult<Item> queryItemList(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "0") Integer rows) {
		return itemService.queryItemList(page, rows);
	}

	@RequestMapping("desc/{id}")
	@ResponseBody
	public ItemDesc queryItemDescById(@PathVariable Long id) {
		return itemDescService.queryById(id);
	}

	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String updateItem(Item item, String desc) {
		itemService.updateItem(item, desc);
		return "ok";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public TaoResult<Item> deleteItem(String ids){
		TaoResult<Item> taoResult = new TaoResult<>();
		try {
			String[] split = ids.split(",");
			for (String id : split) {
				itemService.deleteById(Long.parseLong(id));
			}
			taoResult.setStatus("200");
		} catch (NumberFormatException e) {
			taoResult.setStatus("500");
			e.printStackTrace();
		}
		return taoResult;
	}
	
	@RequestMapping("instock")
	@ResponseBody
	public TaoResult<Item> instockItem(String ids){
		TaoResult<Item> taoResult = new TaoResult<>();
		try {
			String[] split = ids.split(",");
			for (String id : split) {
				Item item = itemService.queryById(Long.parseLong(id));
				item.setStatus(2);
				itemService.updateById(item);
			}
			taoResult.setStatus("200");
		} catch (NumberFormatException e) {
			taoResult.setStatus("500");
			e.printStackTrace();
		}
		return taoResult;
	}
	
	@RequestMapping("reshelf")
	@ResponseBody
	public TaoResult<Item> reshelfItem(String ids){
		TaoResult<Item> taoResult = new TaoResult<>();
		try {
			String[] split = ids.split(",");
			for (String id : split) {
				Item item = itemService.queryById(Long.parseLong(id));
				item.setStatus(1);
				itemService.updateById(item);
			}
			taoResult.setStatus("200");
		} catch (NumberFormatException e) {
			taoResult.setStatus("500");
			e.printStackTrace();
		}
		return taoResult;
	}
}
