package com.insurance.exceptions;

import java.time.LocalDateTime;

public class GlobalErrorResponse {

	private String message;
	private int statusCode;
	private LocalDateTime time;

	public GlobalErrorResponse() {
		super();
	}

	public GlobalErrorResponse(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.time = LocalDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "GlobalErrorResponse [message=" + message + ", statusCode=" + statusCode + ", time=" + time + "]";
	}

}
