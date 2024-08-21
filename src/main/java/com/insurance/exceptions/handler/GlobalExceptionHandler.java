package com.insurance.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.insurance.exceptions.ClaimNotFoundException;
import com.insurance.exceptions.GlobalErrorResponse;
import com.insurance.exceptions.PolicyNotFoundException;
import com.insurance.exceptions.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<GlobalErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
		GlobalErrorResponse error = new GlobalErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<GlobalErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<GlobalErrorResponse> handlePolicyNotFoundException(PolicyNotFoundException e) {
		GlobalErrorResponse error = new GlobalErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<GlobalErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClaimNotFoundException.class)
	public ResponseEntity<GlobalErrorResponse> handlePolicyNotFoundException(ClaimNotFoundException e) {
		GlobalErrorResponse error = new GlobalErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<GlobalErrorResponse>(error, HttpStatus.NOT_FOUND);
	}

}
