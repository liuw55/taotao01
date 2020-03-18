package com.taotao.manager.service;

import java.util.List;

import com.taotao.manager.pojo.ItemCat;

public interface ItemCatService extends BaseService<ItemCat>{
	
	/**
	 * 根据商品类目父id查询数据
	 */
	List<ItemCat> queryItemCatByParentId(Long parentId);
	
	
}
