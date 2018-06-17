package com.novel.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.novel.bean.User;
import com.novel.bean.UserExample;
import com.novel.bean.UserExample.Criteria;
import com.novel.mapper.UserMapper;
import com.novel.redis.RedisClient;
import com.novel.service.UserMangerService;
import com.novel.until.CookieUtils;
import com.novel.until.Msg;

@Service
public class UserMangerServiceimpl implements UserMangerService{

	@Value("${admin}")
	private  String admin;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RedisClient redisClient;
	
	public User login(User user, HttpServletRequest request,HttpServletResponse response) {
		/*MessageDigest  md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String password = md5.digest(user.getPassword().getBytes()).toString();*/
		UserExample example = new UserExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andNameEqualTo(user.getName());
	    criteria.andPasswordEqualTo(user.getPassword());
		List<User> users = userMapper.selectByExample(example);
		if(users.size()!=0) {
			user = users.get(0);;
			user.setLasttime(new Date());
			userMapper.updateByPrimaryKey(user);
			String jsonuser = JSON.toJSONString(users.get(0)).trim();
			try {
				jsonuser = URLEncoder.encode(jsonuser, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//向cookie写入用户
			CookieUtils.setCookie(request, response, "user", jsonuser,3600*24*30);
			return users.get(0);
		}else {
			return null;
		}
	}

	@Override
	public String loginAdmin(User user,HttpServletRequest request,HttpServletResponse response) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(user.getName());
		criteria.andPasswordEqualTo(user.getPassword());
		List<User> adminUser = userMapper.selectByExample(example);
		if(adminUser.size()==0) {
			return "redirect:../view/admin/adminlogin.jsp";
		}
		if(adminUser.get(0).getPower()<1) {
			return "redirect:../view/admin/adminlogin.jsp";
		}
		//写入redis中
		User user2 = adminUser.get(0);
		user2.setLasttime(new Date());
		userMapper.updateByPrimaryKey(user2);
		
		user2.setPassword(null);
		user2.setState(0);
		String userjson = JSON.toJSONString(user2);
		String adminuserkey = "adminuser"+UUID.randomUUID();
		redisClient.set(adminuserkey, userjson);
		redisClient.expire(adminuserkey, 3600);
		//写入cookie
		CookieUtils.setCookie(request, response, admin, adminuserkey);
		try {
			userjson = URLEncoder.encode(userjson, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CookieUtils.setCookie(request, response, "admin", userjson);
		return "admin/title";
	}

	@Override
	public Msg getUsers(Integer pn, String name) {
		
		 PageHelper.startPage(pn, 8);//8为页码大小
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPowerEqualTo(0);
		criteria.andNameLike("%"+name+"%");
		List<User> users = userMapper.selectByExample(example);
		PageInfo pageinfo = new PageInfo(users, 8); 
		return Msg.success().add("pageinfo", pageinfo);
	}

	@Override
	public Msg disabledUser(Integer state, int userid) {
		User user = new User();
		user.setState(state);
		user.setId(userid);
		userMapper.updateByPrimaryKeySelective(user);
		return Msg.success();
	}

	@Override
	public User register() {
		String name = "";
		for(int i=0;i<10;i++) {
			name  = name+new Random().nextInt(10);
		}
		User userByName = getUserByName(name);
		if(userByName==null) {
			User user = new User();
			user.setName(name);
			user.setPower(0);
			user.setPassword("123456");
			user.setNickname(name.substring(0,5));
			return user;
		}
		return null;
	}

	@Override
	public User registersure(User user,HttpServletRequest request,HttpServletResponse response) {
		User userByName = getUserByName(user.getName());
		if(userByName==null) {
			String password = user.getPassword();
			/*MessageDigest  md5 = null;
			try {
				md5 = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//String digest = md5.digest(password.getBytes()).toString();
			user.setPassword(password);
			user.setCreatetime(new Date());
			user.setState(0);
			user.setLasttime(new Date());
			user.setUpdatetime(new Date());
			user.setPower(0);
			userMapper.insert(user);
			String userjson =JSON.toJSONString(user);
			try {
				userjson = URLEncoder.encode(userjson, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CookieUtils.setCookie(request, response, "user", userjson, 3600*24*31);
			return user;
		}
		return null;
	}
	
	public User  getUserByName(String name){
		UserExample example = new  UserExample();
		Criteria criteria =  example.createCriteria();
		criteria.andNameEqualTo(name);
		List<User> selectByExample = userMapper.selectByExample(example);
		if(selectByExample.size()==0) {
			return null;
		}
		return selectByExample.get(0);
	}
	

}
