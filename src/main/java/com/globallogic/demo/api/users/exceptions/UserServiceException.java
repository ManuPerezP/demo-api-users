package com.globallogic.demo.api.users.exceptions;

import org.springframework.http.HttpStatus;

public class UserServiceException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	
	public UserServiceException(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}	

}
