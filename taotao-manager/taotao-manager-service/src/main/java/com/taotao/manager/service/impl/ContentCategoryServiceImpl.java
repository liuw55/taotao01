package com.taotao.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.taotao.manager.pojo.ContentCategory;
import com.taotao.manager.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {

	public List<ContentCategory> queryContentCategoryByParentId(Long parentId) {
		ContentCategory contentCategory = new ContentCategory();
		contentCategory.setParentId(parentId);
		return super.queryListByWhere(contentCategory);
	}

	public ContentCategory saveContentCategory(ContentCategory contentCategory) {
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		ContentCategory parent = super.queryById(contentCategory.getParentId());
		if (!parent.getIsParent()) {
			parent.setIsParent(true);
			super.updateByIdSelective(parent);
		}
		super.saveSelective(contentCategory);
		return contentCategory;
	}

	public void deleteContentCategoryById(Long parentId, Long id) {
		List<Object> ids = new ArrayList<>();
		ids.add(id);
		getDeleteIds(id, ids);
		super.deleteByIds(ids);
		ContentCategory category = new ContentCategory();
		category.setParentId(parentId);
		Integer count = super.queryCountByWhere(category);
		if (count == 0) {
			ContentCategory parent  = new ContentCategory();
			parent.setId(parentId);
			parent.setIsParent(false);
			super.updateByIdSelective(parent);
		}
		
	}

	private void getDeleteIds(Long id, List<Object> ids) {
		ContentCategory category = new ContentCategory();
		category.setParentId(id);
		List<ContentCategory> list = super.queryListByWhere(category);
		if (list != null && list.size() > 0) {
			for (ContentCategory contentCategory : list) {
				ids.add(contentCategory.getId());
				if (contentCategory.getIsParent()) {
					getDeleteIds(contentCategory.getId(), ids);
				}
			}
		}
	}

}
