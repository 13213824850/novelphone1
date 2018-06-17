package com.novel.service;

import javax.servlet.http.HttpServletRequest;

import com.novel.bean.User;

public interface UntilService {
public User getUserRedis(HttpServletRequest request);
}
