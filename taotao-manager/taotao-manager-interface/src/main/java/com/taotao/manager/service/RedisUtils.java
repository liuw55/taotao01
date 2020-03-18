package com.taotao.manager.service;

public interface RedisUtils {
	
	/**
	 * 保存
	 */
	void set(String key, String value);
	
	/**
	 * 根据key查询
	 */
	String get(String key);
	
	/**
	 * 删除
	 */
	void del(String key);
	
	/**
	 * 根据key设置生存时间
	 */
	void expire(String key, Integer seconds);
	
	/**
	 * 保存并设置生存时间
	 */
	void set(String key, String value, Integer seconds);
	
	/**
	 * value加一
	 */
	Long incr(String value);
}
