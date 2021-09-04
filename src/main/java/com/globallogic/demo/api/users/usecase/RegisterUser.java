package com.globallogic.demo.api.users.usecase;

import org.springframework.stereotype.Component;

import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.jsons.response.UserDetails;
import com.globallogic.demo.api.users.service.AuthService;

@Component
public class RegisterUser {
	private AuthService authService;

	public RegisterUser(AuthService authService) {
		this.authService = authService;
	}

	public UserDetails registerUser(UserData createUserRest) throws UserServiceException {
		return authService.registerUser(createUserRest);
	}
}
