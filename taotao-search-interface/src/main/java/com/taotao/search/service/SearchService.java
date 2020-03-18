package com.taotao.search.service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Item;

public interface SearchService {

	TaoResult<Item> search(String query, Integer page, Integer rows);

	void saveItem(long itemId);

}
