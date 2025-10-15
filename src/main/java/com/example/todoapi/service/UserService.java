package com.example.todoapi.service;

import com.example.todoapi.config.PasswordConfig;
import com.example.todoapi.exception.PasswordNotCorrectException;
import com.example.todoapi.exception.UserAlreadyExistException;
import com.example.todoapi.exception.UserNotFoundException;
import com.example.todoapi.repository.UserRepository;
import com.example.todoapi.user.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder hash;

	public User registerUser(User user) {
		if (!((userRepository.findByUsername(user.getUsername()).isEmpty()))) {
			throw new UserAlreadyExistException(
					"Brukeren med brukernavn: " + user.getUsername() + " er allerede en registrert bruker");
		} else {
			String hashedPassword = hash.encode(user.getPassword());
			user.setPassword(hashedPassword);
			userRepository.save(user);
			return user;
		}
	}

	public User loginUser(String username, String password) {
		Optional<User> OptUser = userRepository.findByUsername(username);
		if (OptUser.isEmpty()) {
			throw new UserNotFoundException("Brukeren med brukernavn: " + username + " er ikkje en registrert bruker");
		} else {
			User user = OptUser.get();
			if (hash.matches(password, user.getPassword())){
				return user;
			} else {
				throw new PasswordNotCorrectException("Passordet stemmer ikkje");
			}

		}

	}

	public User getUserByUsername(String username) {
		return (userRepository.findByUsername(username)).orElseThrow(() -> new UserNotFoundException("Brukeren med brukernavn: " + username + " er ikkje en registrert bruker"));

	}

}
