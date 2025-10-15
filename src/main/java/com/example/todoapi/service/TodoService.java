package com.example.todoapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapi.exception.TodoNotFoundException;
import com.example.todoapi.exception.UserNotFoundException;
import com.example.todoapi.model.Todo;
import com.example.todoapi.user.User;
import com.example.todoapi.repository.TodoRepository;
import com.example.todoapi.repository.UserRepository;

@Service
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Todo> getAllTodos() {

	

		return todoRepository.findAll();
	}

	public Todo getTodoById(long id) {
		
		return todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException("Todo med id " + id + " finnes ikkje"));

	}
	
	public List<Todo> getTodosForUser(String username){
		return todoRepository.findByUserUsername(username);
	}

	public Todo addTodo(String username, Todo todo) {
		User user = userRepository.findByUsername(username).orElseThrow( () -> new UserNotFoundException("Bruker: " + username + " er ikkje en registrert bruker"));
		todo.setUser(user);
		return todoRepository.save(todo);
	}

	public Todo updateTodo(long id, Todo todo) {
		
		
		Todo eksisterende = getTodoById(id);
		eksisterende.setTittel(todo.getTittel());
		eksisterende.setBeskrivelse(todo.getBeskrivelse());
		eksisterende.setStatus(todo.getStatus());
		return todoRepository.save(eksisterende);
	}

	public void deleteTodo(long id) {

		todoRepository.deleteById(id);

}}
