package com.rajat.springrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = 2177812434828432373L;
	
	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(String message,String cause) {
		super(message,new Throwable(cause));
	}

}
