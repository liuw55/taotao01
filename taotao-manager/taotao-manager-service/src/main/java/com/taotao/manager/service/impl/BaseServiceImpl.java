package com.taotao.manager.service.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.taotao.manager.pojo.BasePojo;
import com.taotao.manager.service.BaseService;

public class BaseServiceImpl<T extends BasePojo> implements BaseService<T> {

	@Autowired
	private Mapper<T> mapper;

	private Class<T> clazz;

	public BaseServiceImpl() {
		// 获取父类的type
		Type type = this.getClass().getGenericSuperclass();
		// 强转为ParameterizedType,可以使用获取泛型类型的方法
		ParameterizedType pType = (ParameterizedType) type;
		// 获取泛型的class
		this.clazz = (Class<T>) pType.getActualTypeArguments()[0];
	}

	public T queryById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	public List<T> queryAll() {
		return this.mapper.select(null);
	}

	public Integer queryCountByWhere(T t) {
		return this.mapper.selectCount(t);
	}

	public List<T> queryListByWhere(T t) {
		return this.mapper.select(t);
	}

	public List<T> queryListByPage(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		return this.mapper.select(null);
	}

	public T queryOne(T t) {
		return this.mapper.selectOne(t);
	}

	public void save(T t) {
		if (t.getCreated() == null) {
			t.setCreated(new Date());
			t.setUpdated(t.getCreated());
		} else if (t.getUpdated() == null) {
			t.setUpdated(t.getCreated());
		}
		this.mapper.insert(t);
	}

	public void saveSelective(T t) {
		if (t.getCreated() == null) {
			t.setCreated(new Date());
			t.setUpdated(t.getCreated());
		} else if (t.getUpdated() == null) {
			t.setUpdated(t.getCreated());
		}
		this.mapper.insertSelective(t);
	}

	public void updateById(T t) {
		t.setUpdated(new Date());
		this.mapper.updateByPrimaryKey(t);
	}

	public void updateByIdSelective(T t) {
		t.setUpdated(new Date());
		this.mapper.updateByPrimaryKeySelective(t);
	}

	public void deleteById(Long id) {
		this.mapper.deleteByPrimaryKey(id);
	}

	public void deleteByIds(List<Object> ids) {
		// 声明条件
		Example example = new Example(this.clazz);
		example.createCriteria().andIn("id", ids);
		this.mapper.deleteByExample(example);
	}

}
