package com.ks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ks.entity.Task;
import com.ks.entity.User;
import com.ks.exception.UserNotFound;
import com.ks.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/profile")
	public ResponseEntity<User> getProfile(@RequestParam Long id) {
		User byId = userRepository.findById(id).orElseThrow(()->new UserNotFound());
		return ResponseEntity.ok(byId);
	}

	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> getTasks(@RequestParam Long id) {
		User byId = userRepository.findById(id).orElseThrow(()->new UserNotFound());
		return ResponseEntity.ok(byId.getTasks());
	}
}
