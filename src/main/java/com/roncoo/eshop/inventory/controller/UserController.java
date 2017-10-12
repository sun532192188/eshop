package com.roncoo.eshop.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public User getUserInfo() {
		User user = userService.findUserInfo();
		return user;
	}
	
	@RequestMapping("/getUserList")
	@ResponseBody
	public List<User> getUserList() {
		return userService.queryUserList();
	}
	
	@RequestMapping("/getCachedUserInfo")
	@ResponseBody
	public User getCachedUserInfo() {
	    User user = userService.getCachedUserInfo();
	    return user;
	}
}
