package com.taotao.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.manager.mapper.ItemCatMapper;
import com.taotao.manager.pojo.ItemCat;
import com.taotao.manager.service.ItemCatService;
@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {

	public List<ItemCat> queryItemCatByParentId(Long parentId) {
		ItemCat param = new ItemCat();
		param.setParentId(parentId);
		return super.queryListByWhere(param);
	}
	
	
}
