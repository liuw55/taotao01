package com.taotao.manager.service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;

public interface ItemService extends BaseService<Item> {
	
	/**
	 * 保存商品
	 */
	void saveItem(Item item, String desc);

	TaoResult<Item> queryItemList(Integer page, Integer rows);
	
	void updateItem(Item item, String desc);
}
