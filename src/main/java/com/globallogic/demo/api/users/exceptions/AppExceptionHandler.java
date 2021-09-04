package com.globallogic.demo.api.users.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.globallogic.demo.api.users.jsons.response.ErrorMessage;

@RestControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

		String errorMessageDescription = ex.getLocalizedMessage();
		if (errorMessageDescription == null)
			errorMessageDescription = ex.toString();

		HttpStatus CODE = ex instanceof UserServiceException ? ((UserServiceException) ex).getStatus()
				: HttpStatus.INTERNAL_SERVER_ERROR;

		ErrorMessage errorMessage = new ErrorMessage(errorMessageDescription);

		return new ResponseEntity<>(errorMessage, new HttpHeaders(), CODE);

	}
}
