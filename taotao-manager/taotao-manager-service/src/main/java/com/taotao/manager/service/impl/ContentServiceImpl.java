package com.taotao.manager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaoResult;
import com.taotao.manager.pojo.Content;
import com.taotao.manager.service.ContentService;
import com.taotao.manager.service.RedisUtils;

@Service
public class ContentServiceImpl extends BaseServiceImpl<Content> implements ContentService {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private RedisUtils redisUtils;
	@Value("${TAOTAO_PROTAL_AD}")
	private String TAOTAO_PROTAL_AD;

	public TaoResult<Content> queryContentByPage(Integer page, Integer rows, Long categoryId) {
		PageHelper.startPage(page, rows);
		Content content = new Content();
		content.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(content);
		PageInfo<Content> pageInfo = new PageInfo<>(list);
		TaoResult<Content> taoResult = new TaoResult<>();
		taoResult.setTotal(pageInfo.getTotal());
		taoResult.setRows(list);
		return taoResult;
	}

	public String queryAD(Long categoryId) {

		// 先去缓存里取
		try {
			String value = redisUtils.get(TAOTAO_PROTAL_AD);
			if (StringUtils.isNotBlank(value)) {
				// 取到了就直接返回
				return value;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		Content param = new Content();
		param.setCategoryId(categoryId);
		List<Content> list = super.queryListByWhere(param);
		List<Map<String, Object>> results = new ArrayList<>();
		for (Content content : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("srcB", content.getPic());
			map.put("height", 240);
			map.put("alt", "");
			map.put("width", 670);
			map.put("src", content.getPic());
			map.put("widthB", 550);
			map.put("href", content.getUrl());
			map.put("heightB", 240);
			results.add(map);
		}
		String json = "";
		try {
			json = MAPPER.writeValueAsString(results);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			redisUtils.set(TAOTAO_PROTAL_AD, json, 60 * 60 * 24);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
