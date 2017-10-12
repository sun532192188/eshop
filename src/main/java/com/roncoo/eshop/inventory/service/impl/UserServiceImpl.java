package com.roncoo.eshop.inventory.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.inventory.dao.RedisDao;
import com.roncoo.eshop.inventory.mapper.UserMapper;
import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;

/**
 * 用户Service实现类
 * @author Administrator
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private RedisDao redisDao;

	@Override
	public User findUserInfo() {
		return userMapper.findUserInfo();
	}

	@Override
	public User getCachedUserInfo() {
		redisDao.set("cached_user_lisi", "{\"name\": \"lisi\", \"age\":28}");
		
		String userJSON = redisDao.get("cached_user_lisi");  
		JSONObject userJSONObject = JSONObject.parseObject(userJSON);
		
		User user = new User();
		user.setName(userJSONObject.getString("name"));   
		user.setAge(userJSONObject.getInteger("age"));  
		
		return user;
	}

	@Override
	public List<User> queryUserList() {
		return userMapper.queryUserList();
	}

	

}
