package com.taotao.manager.service;

import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;

public interface ContentService extends BaseService<Content>{
	
	/**
	 * 根据分类ID分页查询内容
	 */
	TaoResult<Content> queryContentByPage(Integer page, Integer rows, Long categoryId);
	
	/**
	 * 根据内容分类查询内容
	 */
	String queryAD(Long categoryId);
}
