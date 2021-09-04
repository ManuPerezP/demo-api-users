package com.globallogic.demo.api.users.jsons.request;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
	@JsonProperty("email")
	@NotBlank(message = "email es necesario")
	private String email;
	
	@JsonProperty("password")
	@NotBlank(message = "password es necesario")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
