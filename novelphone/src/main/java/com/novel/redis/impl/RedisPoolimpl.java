package com.novel.redis.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novel.redis.RedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisPoolimpl implements RedisClient {
	@Autowired
	private JedisPool jedisPool;
	@Override
	public String get(String key) {
		Jedis jedis = jedisPool.getResource();
		String value = jedis.get(key);
		jedis.close();
		return value;
	}
	@Override
	public Map<String, String> hGetAll(String key) {
		Jedis jedis = jedisPool.getResource();
	     Map<String, String> hgetAll = jedis.hgetAll(key);
		jedis.close();
		return hgetAll;
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = jedisPool.getResource();
		String result = jedis.set(key, value);
		jedis.close();
		return result;
	}

	@Override
	public long incr(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.incr(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = jedisPool.getResource();
		Long hset = jedis.hset(hkey, key, value);
		jedis.close();
		return hset;
	}

	@Override
	public String hget(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		String hget = jedis.hget(hkey, key);
		jedis.close();
		return hget;
	}

	@Override
	public Long del(String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.del(key);
		jedis.close();
		return result;
	}

	@Override
	public Long hdel(String hkey, String key) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.hdel(hkey, key);
		jedis.close();
		return result;
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = jedisPool.getResource();
		Long result = jedis.expire(key, second);
		jedis.close();
		return result;
	}
	@Override
	public Double Zincrby(String key, String member, Double incrse) {
		Jedis jedis = jedisPool.getResource();
		Double zincrby = jedis.zincrby(key, incrse, member);
		jedis.close();
		return zincrby;
	}
	public List<Integer> getZincrB(String key,int start ,int end) {
		Jedis jedis = jedisPool.getResource();
		Set<String> novelsSet = jedis.zrevrange(key, start, end);
		List<Integer> lists = new ArrayList<Integer>();
		for (String string : novelsSet) {
			lists.add(Integer.parseInt(string));
		}
		jedis.close();
		return lists;
	}
}
