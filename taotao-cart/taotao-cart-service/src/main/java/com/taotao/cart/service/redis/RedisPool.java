package com.taotao.cart.service.redis;

import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.cart.service.RedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
	
public class RedisPool implements RedisUtils {
	
	@Autowired
	private JedisPool jedisPool;
	
	public void set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		jedis.close();
	}

	
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}

	
	public void del(String key) {
		Jedis jedis = jedisPool.getResource();
		jedis.del(key);
		jedis.close();
	}

	
	public void expire(String key, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		jedis.expire(key, seconds);
		jedis.close();
	}

	
	public void set(String key, String value, Integer seconds) {
		Jedis jedis = jedisPool.getResource();
		jedis.set(key, value);
		jedis.expire(key, seconds);
		jedis.close();
	}

	
	public Long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long count = jedis.incr(key);
		jedis.close();
		return count;
	}

}
