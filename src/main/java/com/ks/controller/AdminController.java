package com.ks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.entity.User;
import com.ks.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		String saveUser = adminService.saveUser(user);
		return new ResponseEntity<String>(saveUser, HttpStatus.CREATED);
	}

	@PutMapping("/updateUser/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
		String updateUser = adminService.updateUser(id, user);
		return new ResponseEntity<String>(updateUser, HttpStatus.OK);
		
	}

	@GetMapping("/readUser/{id}")
	public ResponseEntity<User> readUser(@PathVariable Long id) {
		User user = adminService.getUser(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		adminService.deleteById(id);
		return new ResponseEntity<String>("Deleted", HttpStatus.NO_CONTENT);
	}

	@PostMapping("/assignRole/{userId}/{roleName}")
	public ResponseEntity<String> assignRole(@PathVariable Long userId, @PathVariable String roleName) {
		String assignRole = adminService.assignRole(userId,roleName);
		return new ResponseEntity<String>(assignRole, HttpStatus.OK);
	}
}
