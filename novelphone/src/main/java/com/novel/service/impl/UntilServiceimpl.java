package com.novel.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.novel.bean.User;
import com.novel.redis.RedisClient;
import com.novel.service.UntilService;
import com.novel.until.CookieUtils;

@Service
public class UntilServiceimpl implements UntilService {

	@Value("${admin}")
	private String admin;
	@Autowired
	private RedisClient redisClient;
	
	@Override
	public User getUserRedis(HttpServletRequest request) {
		String cookieValue = CookieUtils.getCookieValue(request, admin);
		if(cookieValue==null) {
			return null;
		}
		String string = redisClient.get(cookieValue);
		redisClient.expire(cookieValue, 1800);
		JSON json = (JSON) JSON.parse(string);
		User user = JSON.toJavaObject(json, User.class);
		return user;
	}

}
