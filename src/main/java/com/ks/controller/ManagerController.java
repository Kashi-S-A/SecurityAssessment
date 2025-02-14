package com.ks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.entity.Task;
import com.ks.entity.User;
import com.ks.service.ManagerService;

@RestController
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		 List<User> allUsers = managerService.getAllUsers();
		return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
	}

	@PostMapping("/assignTask/{userId}")
	public ResponseEntity<String> assignTask(@PathVariable Long userId, @RequestBody Task task) {
		 String assignTask = managerService.assignTask(userId,task);
		 return new ResponseEntity<String>(assignTask, HttpStatus.OK);
	}
}
