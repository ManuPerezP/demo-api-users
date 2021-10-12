package com.globallogic.demo.api.users.jsons.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.globallogic.demo.api.users.jsons.Phones;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetails {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;

	@JsonProperty("email")
	private String email;

	@JsonProperty("phones")
	private List<Phones> phones;

	@JsonProperty("created")
	private LocalDateTime created;
	
	@JsonProperty("modified")
	private LocalDateTime modified;

	@JsonProperty("last_login")
	private LocalDateTime lastLogin;

	@JsonProperty("token")
	private String token;

	@JsonProperty("isactive")
	private Boolean isActive;
	
	
	
	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", name=" + name + ", email=" + email + ", phones=" + phones + ", created="
				+ created + ", modified=" + modified + ", lastLogin=" + lastLogin + ", token=" + token + ", isActive="
				+ isActive + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

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

	public List<Phones> getPhones() {
		return phones;
	}

	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}
	
	

}
