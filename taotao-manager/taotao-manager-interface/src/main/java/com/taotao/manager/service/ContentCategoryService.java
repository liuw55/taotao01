package com.taotao.manager.service;

import java.util.List;

import com.taotao.manager.pojo.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{
	
	/**
	 * 根据parentId查询内容分类
	 */
	List<ContentCategory> queryContentCategoryByParentId(Long parentId);
	
	/**
	 * 新增
	 */
	ContentCategory saveContentCategory(ContentCategory contentCategory);
	
	/**
	 * 删除
	 */
	void deleteContentCategoryById(Long parentId, Long id);
}
