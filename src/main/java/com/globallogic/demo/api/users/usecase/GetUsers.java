package com.globallogic.demo.api.users.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.service.UserService;

@Component
public class GetUsers {

	private UserService userService;

	public GetUsers(UserService userService) {
		this.userService = userService;
	}

	public List<User> getAll() throws UserServiceException {
		return userService.getAllUsers();
	}
}
