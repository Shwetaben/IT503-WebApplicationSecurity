package com.webapplication.security.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.webapplication.security.exception.XSSAttackFoundException;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = { XSSAttackFoundException.class })
	public ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

		return new ResponseEntity<Object>(ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}
}
