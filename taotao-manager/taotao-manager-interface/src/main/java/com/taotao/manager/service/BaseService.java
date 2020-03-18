package com.taotao.manager.service;

import java.util.List;

public interface BaseService<T> {
	
	/**
	 * 根据id查询数据
	 */
	T queryById(Long id);
	
	/**
	 * 查询所有数据
	 */
	List<T> queryAll();
	
	/**
	 * 根据条件查询数据条数
	 */
	Integer queryCountByWhere(T t);
	
	/**
	 * 根据条件查询数据
	 */
	List<T> queryListByWhere(T t);
	
	/**
	 * 分页查询数据
	 */
	List<T> queryListByPage(Integer page, Integer rows);
	
	/**
	 * 根据条件查询一条数据
	 */
	T queryOne(T t);
	
	/**
	 * 新增
	 */
	void save(T t);
	
	/**
	 * 新增,忽略空参数
	 */
	void saveSelective(T t);
	
	/**
	 * 根据主键更新
	 */
	void updateById(T t);
	
	/**
	 * 根据主键更新,忽略空参数
	 */
	void updateByIdSelective(T t);
	
	/**
	 * 根据id删除数据
	 */
	void deleteById(Long id);
	
	/**
	 * 根据ids批量删除数据
	 */
	void deleteByIds(List<Object> ids);
}
