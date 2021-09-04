package com.globallogic.demo.api.users.usecase;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.service.UserService;

@Component
public class UpdateUser {
	private UserService userService;

	public UpdateUser(UserService userService) {
		this.userService = userService;
	}

	public User update(UserData createUserRest, UUID id) throws UserServiceException {
		return userService.update(createUserRest, id);
	}
}