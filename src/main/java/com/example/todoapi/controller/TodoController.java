package com.example.todoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.dto.TodoRequestDTO;
import com.example.todoapi.dto.TodoResponseDTO;
import com.example.todoapi.model.Todo;
import com.example.todoapi.security.JwtUtil;
import com.example.todoapi.service.TodoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@Autowired
	private TodoService todoService;
	private final JwtUtil jwtUtil;

	public TodoController(TodoService todoService, JwtUtil jwtUtil) {
		this.todoService = todoService;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping
	public List<TodoResponseDTO> getAllTodos(Authentication auth) {
		String username = auth.getName();
		List<Todo> todoListe = todoService.getTodosForUser(username);
		
		return todoListe.stream()
				.map(todo -> new TodoResponseDTO(
						todo.getId(),
						todo.getTittel(),
						todo.getBeskrivelse(),
						todo.getStatus()))
				.toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable long id) {
		Todo todo = todoService.getTodoById(id);
		TodoResponseDTO response = new TodoResponseDTO(todo.getId(), todo.getTittel(), todo.getBeskrivelse(), todo.getStatus());
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<TodoResponseDTO> createTodo(Authentication auth, @Valid @RequestBody TodoRequestDTO request) {
		String username = auth.getName();
		Todo todo = new Todo(request.getTittel(), request.getBeskrivelse(), request.getStatus());
		Todo saved = todoService.addTodo(username, todo);
		TodoResponseDTO response = new TodoResponseDTO(saved.getId(), saved.getTittel(), saved.getBeskrivelse(), saved.getStatus());
		return ResponseEntity.ok(response);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TodoResponseDTO> updateTodo(@PathVariable long id, @RequestBody TodoRequestDTO request) {
		Todo todo = new Todo(request.getTittel(), request.getBeskrivelse(), request.getStatus());
		todo = todoService.updateTodo(id, todo);
		TodoResponseDTO response = new TodoResponseDTO(todo.getId(), todo.getTittel(), todo.getBeskrivelse(), todo.getStatus());
		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public void slettTodo(@PathVariable long id) {
		todoService.deleteTodo(id);
	}

}
