package com.globallogic.demo.api.users.usecase;

import org.springframework.stereotype.Component;

import com.globallogic.demo.api.users.jsons.request.Login;
import com.globallogic.demo.api.users.jsons.response.UserDetails;
import com.globallogic.demo.api.users.service.AuthService;

@Component
public class LoginUser {

	private AuthService authService;

	public LoginUser(AuthService authService) {
		this.authService = authService;
	}

	public UserDetails login(Login loginRequest) {
		return authService.login(loginRequest);
	}
}
