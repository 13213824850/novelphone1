package com.novel.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisClient {
	String get(String key);
	String set(String key, String value);
	long incr(String key);
	Long hset(String hkey, String key, String value);
	String hget(String hkey, String key);
	Long del(String key);
	Long hdel(String hkey, String key);
	Long expire(String key, int second);
	Map<String, String> hGetAll(String key);
	Double Zincrby(String key, String member, Double incrse);
	List<Integer> getZincrB(String key, int start, int end);
}

