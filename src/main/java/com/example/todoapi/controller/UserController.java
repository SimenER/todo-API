package com.example.todoapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.dto.user.*;
import com.example.todoapi.security.JwtUtil;
import com.example.todoapi.service.UserService;
import com.example.todoapi.user.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")

public class UserController {
	
	
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	
	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO request) {
		User user = new User(request.getUsername(), request.getPassword());
		user = userService.registerUser(user);
		UserResponseDTO response = new UserResponseDTO(user.getId(), user.getUsername());
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody UserRequestDTO request) {
		User user = userService.loginUser(request.getUsername(), request.getPassword());
		String token = jwtUtil.generateToken(user);
		
		Map<String, Object> response = new HashMap<>();
		response.put("username", user.getUsername());
		response.put("token", token);
		
		return ResponseEntity.ok(response);
		
	
	}
	
	
	
	@GetMapping("/{username}")
	public ResponseEntity<UserResponseDTO> getUserByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		UserResponseDTO response = new UserResponseDTO(user.getId(), user.getUsername());
		return ResponseEntity.ok(response);
	}

}
