package com.globallogic.demo.api.users.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.demo.api.users.entity.User;
import com.globallogic.demo.api.users.exceptions.UserServiceException;
import com.globallogic.demo.api.users.jsons.request.UserData;
import com.globallogic.demo.api.users.usecase.DeleteUser;
import com.globallogic.demo.api.users.usecase.GetUsers;
import com.globallogic.demo.api.users.usecase.UpdateUser;

@RestController
@RequestMapping(path = "/api-users" + "/v1")
public class UserRestController {
	@Autowired
	private GetUsers getUsers;
	@Autowired
	private UpdateUser updateUser;
	@Autowired
	private DeleteUser deleteUser;

	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<User>> getAllUsers() throws UserServiceException {
		return new ResponseEntity<>(getUsers.getAll(), HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) throws UserServiceException {
		deleteUser.remove(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<User> replaceUser(@RequestBody UserData createUserRest, @PathVariable String id) throws UserServiceException {
		return new ResponseEntity<User>(updateUser.update(createUserRest, UUID.fromString(id)), HttpStatus.OK);
	}

}
