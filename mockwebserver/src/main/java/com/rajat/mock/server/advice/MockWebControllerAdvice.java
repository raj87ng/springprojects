package com.rajat.mock.server.advice;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.rajat.mock.server.response.RestApiErrorResponse;

@RestControllerAdvice
public class MockWebControllerAdvice {

	
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing.";
		RestApiErrorResponse apiError = new RestApiErrorResponse(
			      HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<Object>(apiError,new HttpHeaders(), apiError.getStatus());
	}
	
	protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

	    RestApiErrorResponse apiError = new RestApiErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
			  HttpMediaTypeNotSupportedException ex, 
			  HttpHeaders headers, 
			  HttpStatus status, 
			  WebRequest request) {
			    StringBuilder builder = new StringBuilder();
			    builder.append(ex.getContentType());
			    builder.append(" media type is not supported. Supported media types are ");
			    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

			    RestApiErrorResponse apiError = new RestApiErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, 
			      ex.getLocalizedMessage(), builder.substring(0, builder.length() - 2));
			    return new ResponseEntity<Object>(
			      apiError, new HttpHeaders(), apiError.getStatus());
			}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	  //  List<String> errors = new ArrayList<String>();
	    
	    List<String> errors = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
		/*
		 * for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
		 * errors.add(violation.getRootBeanClass().getName() + " " +
		 * violation.getPropertyPath() + ": " + violation.getMessage()); }
		 */

	    RestApiErrorResponse apiError = 
	      new RestApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();

	    RestApiErrorResponse apiError = 
	      new RestApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		RestApiErrorResponse apiError = new RestApiErrorResponse(
	      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	
}
