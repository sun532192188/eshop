package com.roncoo.eshop.inventory.mapper;

import java.util.List;

import com.roncoo.eshop.inventory.model.User;

public interface UserMapper {

	public User findUserInfo();
	
	public List<User> queryUserList();
	
	
}
