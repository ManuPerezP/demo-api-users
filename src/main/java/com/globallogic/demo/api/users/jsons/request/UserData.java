
package com.globallogic.demo.api.users.jsons.request;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globallogic.demo.api.users.jsons.Phones;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData {
	
	@JsonProperty("name")
	@NotBlank(message = "el nombre es necesario")
	private String name;
	
	@JsonProperty("email")
	@NotBlank(message = "email es necesario")
	private String email;
	
	@JsonProperty("password")
	@NotBlank(message = "el password es necesario")
	private String password;
	
	@JsonProperty("phones")
	@NotEmpty(message = "al menos un telefono es necesario")
	private List<@Valid Phones> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public List<Phones> getPhones() {
		return phones;
	}

	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "CreateUserRest [name=" + name + ", email=" + email + ", password=" + password + ", phones=" + phones.stream().map(p->p.toString())
				+ "]";
	}
	
	
	
	
	
}
