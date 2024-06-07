package com.SpringBoot.BlogApp.ExceptionsHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.SpringBoot.BlogApp.Utils.ApiResponse;

@RestControllerAdvice
public class GlobalExcceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHanlder(ResourceNotFoundException rex){
		ApiResponse apiRes = new ApiResponse(rex.getMessage(),false);
		return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.NOT_FOUND);
	}
}
