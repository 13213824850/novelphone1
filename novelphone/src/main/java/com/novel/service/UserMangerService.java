package com.novel.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.novel.bean.User;
import com.novel.until.Msg;

public interface UserMangerService {
	public User login(User user, HttpServletRequest request, HttpServletResponse response);

	public String loginAdmin(User user, HttpServletRequest request, HttpServletResponse response);

	public Msg getUsers(Integer pn, String name);

	public Msg disabledUser(Integer state, int userid);

	public User register();

	public User registersure(User user, HttpServletRequest request, HttpServletResponse response);
		
}
