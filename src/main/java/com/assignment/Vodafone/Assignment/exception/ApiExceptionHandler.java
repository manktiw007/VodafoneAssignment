package com.assignment.Vodafone.Assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler({ ApiException.class })

	public ResponseEntity<DeviceApiResponse> apiExceptionHandle(ApiException exception) {
		DeviceApiResponse resp = new DeviceApiResponse();
		resp.setDescription(exception.getDescription());
		if (exception.getStatus() == HttpStatus.NOT_FOUND) {
			return new ResponseEntity<DeviceApiResponse>(resp, HttpStatus.NOT_FOUND);
		} else if (exception.getStatus() == HttpStatus.BAD_REQUEST) {
			return new ResponseEntity<DeviceApiResponse>(resp, HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<DeviceApiResponse>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
