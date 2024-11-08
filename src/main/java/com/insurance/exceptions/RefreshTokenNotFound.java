package com.insurance.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RefreshTokenNotFound extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -7802960058056122250L;

	public RefreshTokenNotFound(String message) {
		super(message);
	}

}
