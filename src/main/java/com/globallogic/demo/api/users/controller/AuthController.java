package com.globallogic.demo.api.users.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.jsons.request.Login;
import com.globallogic.demo.api.users.jsons.response.UserDetails;
import com.globallogic.demo.api.users.usecase.LoginUser;
import com.globallogic.demo.api.users.usecase.RegisterUser;

@RestController
@RequestMapping("/api-users/auth")
public class AuthController {
	@Autowired
	private RegisterUser createUser;
	@Autowired
	private LoginUser loginUser;

	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDetails loginUser(@Valid @RequestBody Login loginRequest, BindingResult result)throws UserServiceException {
		if (result.hasErrors()) {
			throw new UserServiceException(result.getAllErrors().get(0).getDefaultMessage().toString(),
					HttpStatus.NOT_ACCEPTABLE);
		}
		return loginUser.login(loginRequest);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public UserDetails registerUser(@Valid @RequestBody UserData userData, BindingResult result)throws UserServiceException {
		if (result.hasErrors()) {
			throw new UserServiceException(result.getAllErrors().get(0).getDefaultMessage().toString(),
					HttpStatus.NOT_ACCEPTABLE);
		}
		return createUser.registerUser(userData);

	}
}
