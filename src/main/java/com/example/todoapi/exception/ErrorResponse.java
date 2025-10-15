package com.example.todoapi.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
	
	
	public ErrorResponse(HttpStatus status, String message, String path) {
		this.timestamp = LocalDateTime.now();
		this.status = status.value();
		this.error = status.getReasonPhrase();
		this.message = message;
		this.path = path;
		
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public int getStatus() {
		return status;
	}


	public String getError() {
		return error;
	}


	public String getMessage() {
		return message;
	}


	public String getPath() {
		return path;
	}

}
