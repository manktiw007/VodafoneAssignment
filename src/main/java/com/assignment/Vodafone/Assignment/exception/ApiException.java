package com.assignment.Vodafone.Assignment.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	
	private HttpStatus status;
	private String description;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public ApiException() {
		super();
	}

	public ApiException(HttpStatus status, String description) {
		super();
		this.status = status;
		this.description = description;
	}

	@Override
	public String toString() {
		return "ApiException [status=" + status + ", description=" + description + "]";
	}
	
	

}
