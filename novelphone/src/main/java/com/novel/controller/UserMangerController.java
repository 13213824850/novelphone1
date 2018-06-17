package com.novel.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.novel.bean.User;
import com.novel.service.UntilService;
import com.novel.service.UserMangerService;
import com.novel.until.Msg;

@Controller
@RequestMapping("/user/")
public class UserMangerController {
	private static final String baseUrl = " http://localhost:8080/novel1/";
	@Autowired
	private UserMangerService userMangerService;
	@Autowired
	private UntilService untilService;
	
	/*
	 * 登录
	 */
	@RequestMapping("login")
	public String hello(User user,HttpServletRequest request,HttpServletResponse response) {
		  User user1 = userMangerService.login(user, request, response);
		  
		  if(user1==null) {
			  request.setAttribute("msg", "用户不存在");
			  return "login";
		  }else {
			  if(user1.getState()==1) {
				  request.setAttribute("msg", "用户被禁用请联系管理员解禁");
				    return "login";
			  }
			  return "forward:/novel/getReadRecord.html?userid="+user1.getId();
		  }
	}
	
	/*
	 * 一键注册
	 */
	@RequestMapping("getNamePassword")
	public String register(HttpServletRequest request) {
		 User user1 = userMangerService.register();
		 if(user1!=null) {
			 request.setAttribute("user", user1);
		 }
		return "register";
	}
	/*
	 * 一键注册
	 */
	@ResponseBody
	@RequestMapping("registersure")
	public Msg registersure(User user,HttpServletRequest request,HttpServletResponse response) {
		User user1 = userMangerService.registersure(user,request,response);
		if(user1==null) {
			Msg.fail("用户已存在");
		}
		return Msg.success();
	}
	
	
	/*
	 * 管理员登录
	 */
	@RequestMapping("loginadmin")
	public String loginAdmin(User user,HttpServletRequest request,HttpServletResponse response) {
		return userMangerService.loginAdmin(user,request,response);
	}
	
	/*
	 * 查询用户信息
	 * pn 页码
	 * name 用户名为null 查询全部
	 */
	@RequestMapping("getUsers")
	@ResponseBody
	public Msg getUsers(@RequestParam(value = "pn",defaultValue="1")Integer pn,@RequestParam(value="name",required=false)String name,HttpServletRequest request) {
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		
		return userMangerService.getUsers(pn,name);
	}
	@RequestMapping("disabledUser")
	@ResponseBody
	public Msg disabledUser(@RequestParam("state")Integer state,@RequestParam("userid")int userid,HttpServletRequest request) {
		
		User user = untilService.getUserRedis(request);
		if(user==null) {
			return Msg.fail("未登录");
		}
		return userMangerService.disabledUser(state,userid);
	}
	
	
}
