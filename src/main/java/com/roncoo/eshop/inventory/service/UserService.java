package com.roncoo.eshop.inventory.service;

import java.util.List;

import com.roncoo.eshop.inventory.model.User;

public interface UserService {

	/**
	 * 查询用户信息
	 * @return 用户信息
	 */
	public User findUserInfo();
	
	public List<User> queryUserList();
	
	/**
	 * 查询redis中缓存的用户信息
	 * @return
	 */
	public User getCachedUserInfo();
	
}
