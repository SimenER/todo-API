package com.example.todoapi.dto.user;

import jakarta.validation.constraints.NotNull;

public class UserResponseDTO {


	private long id;
	private String username;
	
	public UserResponseDTO(long id, String username) {
		this.id = id;
		this.username = username;
	}

	public long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

}
