package com.rajat.springrest.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

	 public static ResponseEntity<Object> build(RestApiErrorResponse apiError) {
         return new ResponseEntity<>(apiError, new HttpHeaders(),apiError.getStatus());
   }
	 
}
