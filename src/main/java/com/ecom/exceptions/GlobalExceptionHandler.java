package com.ecom.exceptions;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.criterion.Example;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ecom.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> handleBadCredentialsException(BadCredentialsException ex){
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}

	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ApiResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
		ApiResponse apiResponse = new ApiResponse(ex.getMessage(),false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
	
		Map<String, String> map = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String message = error.getDefaultMessage();
			String fieldName = ((FieldError) error).getField();
			map.put(message, fieldName);
		});
		// put all messages to map
		
		return new ResponseEntity<Map<String, String>>(map,HttpStatus.NOT_FOUND);
	}
	

}
