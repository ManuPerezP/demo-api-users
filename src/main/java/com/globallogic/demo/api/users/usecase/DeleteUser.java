package com.globallogic.demo.api.users.usecase;

import org.springframework.stereotype.Component;

import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.service.UserService;

@Component
public class DeleteUser {
	private UserService userService;

	public DeleteUser(UserService userService) {
		this.userService = userService;
	}

	public void remove(String id) throws UserServiceException {
		userService.delete(id);
	}
}
