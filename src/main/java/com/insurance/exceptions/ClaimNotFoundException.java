package com.insurance.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClaimNotFoundException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -7802960058056122250L;

	public ClaimNotFoundException(String message) {
		super(message);
	}

}
