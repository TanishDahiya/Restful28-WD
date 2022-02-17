package com.mins.practice.Exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mins.practice.entities.ExceptionResponse;

@ControllerAdvice //The @ControllerAdvice annotation was first introduced in Spring 3.2. It allows you to handle exceptions across the whole application
@RestController
public class CustomisedExceptionHandler extends ResponseEntityExceptionHandler{
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)//Handle all the exceptions
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse =new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	//For user not found exception
	@ExceptionHandler(UserNotFoundException.class)//Handle all the exceptions
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,WebRequest request) {
		ExceptionResponse exceptionResponse =new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
	
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse =new ExceptionResponse(new Date(),"Validation failed",ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
