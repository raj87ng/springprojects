package com.rajat.springrest.advice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MimeType;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rajat.springrest.exception.BadRequestException;
import com.rajat.springrest.response.ResponseEntityBuilder;
import com.rajat.springrest.response.RestApiErrorResponse;



@RestControllerAdvice
public class RestClientControllerAdvice extends ResponseEntityExceptionHandler{

	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " parameter is missing.";
		RestApiErrorResponse apiError = new RestApiErrorResponse(
			      HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return ResponseEntityBuilder.build(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

	    RestApiErrorResponse apiError = new RestApiErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
	    return ResponseEntityBuilder.build(apiError);
	}
	
	@Override
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
			    return ResponseEntityBuilder.build(apiError);
			}
	
	/*
	 * @ExceptionHandler(HttpMediaTypeNotSupportedException.class) public
	 * ResponseEntity<Map<String, String>> handleException(
	 * HttpMediaTypeNotSupportedException e) {
	 * 
	 * String provided = e.getContentType().toString(); List<String> supported =
	 * e.getSupportedMediaTypes().stream() .map(MimeType::toString)
	 * .collect(Collectors.toList());
	 * 
	 * String error = provided + " is not one of the supported media types (" +
	 * String.join(", ", supported) + ")";
	 * 
	 * Map<String, String> errorResponse = new HashMap<>();
	 * errorResponse.put("error", error); errorResponse.put("message",
	 * e.getLocalizedMessage()); errorResponse.put("status",
	 * HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString());
	 * 
	 * return new ResponseEntity<>(errorResponse,
	 * HttpStatus.UNSUPPORTED_MEDIA_TYPE); }
	 */
	
	
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
	    return ResponseEntityBuilder.build(apiError);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Map<String, String>> handleException(
	        HttpRequestMethodNotSupportedException e) throws IOException {
	    Map<String, String> errorResponse = new HashMap<>();
	    String provided = e.getMethod();
	    List<String> supported = Arrays.asList(e.getSupportedMethods());

	    String error = provided + " is not one of the supported Http Methods (" +
	            String.join(", ", supported) + ")";
	    errorResponse.put("error", error);
	    errorResponse.put("message", e.getLocalizedMessage());
	    errorResponse.put("status", HttpStatus.METHOD_NOT_ALLOWED.toString());

	    return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Map<String, String>> handleException(
	        HttpMessageNotReadableException e) throws IOException {

	    Map<String, String> errorResponse = new HashMap<>();
	    errorResponse.put("message", e.getLocalizedMessage());
	    errorResponse.put("status", HttpStatus.BAD_REQUEST.toString());

	    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String error = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();

	    RestApiErrorResponse apiError = 
	      new RestApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
	    return ResponseEntityBuilder.build(apiError);
	}
	
	@Override
	 protected ResponseEntity<Object> handleMethodArgumentNotValid(
             MethodArgumentNotValidException ex,
             HttpHeaders headers, 
             HttpStatus status, 
             WebRequest request) {
         
         List<String> details = new ArrayList<String>();
         details = ex.getBindingResult()
                     .getFieldErrors()
                     .stream()
                     .map(error -> error.getObjectName()+ " : " +error.getDefaultMessage())
                     .collect(Collectors.toList());
         
         RestApiErrorResponse err = new RestApiErrorResponse(
       //      LocalDateTime.now(),
             HttpStatus.BAD_REQUEST, 
             "Validation Errors" ,
             details);
         
         return ResponseEntityBuilder.build(err);
     }
	 
	/*
	 * @ExceptionHandler({ BindException.class,
	 * MethodArgumentNotValidException.class }) public ResponseEntity<Map<String,
	 * Object>> handleException(BindException e) {
	 * 
	 * List<String> errors = new ArrayList<>(); e.getFieldErrors() .forEach(err ->
	 * errors.add(err.getField() + ": " + err.getDefaultMessage()));
	 * e.getGlobalErrors() .forEach(err -> errors.add(err.getObjectName() + ": " +
	 * err.getDefaultMessage()));
	 * 
	 * Map<String, Object> errorResponse = new HashMap<>();
	 * errorResponse.put("error", errors);
	 * 
	 * errorResponse.put("message", e.getLocalizedMessage());
	 * errorResponse.put("status", HttpStatus.BAD_REQUEST.toString()); return new
	 * ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST); }
	 */
	
	
	
	@ExceptionHandler({ BadRequestException.class })
	public ResponseEntity<Object> handleBadException(Exception ex) {
		RestApiErrorResponse apiError = new RestApiErrorResponse(
				HttpStatus.BAD_REQUEST, ex.getMessage(),"Bad Request");
	    return ResponseEntityBuilder.build(apiError);
	}
	
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		RestApiErrorResponse apiError = new RestApiErrorResponse(
	      HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "error occurred");
	    return ResponseEntityBuilder.build(apiError);
	}
}
