package com.ks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ks.entity.Task;
import com.ks.entity.User;
import com.ks.exception.UserNotFound;
import com.ks.repository.UserRepository;

@Service
public class ManagerService {

	@Autowired
	private UserRepository repository;

	public List<User> getAllUsers() {
		return repository.findAll();
	}

	public String assignTask(Long userId, Task task) {
		User user = repository.findById(userId).orElseThrow(()->new UserNotFound());
		List<Task> tasks = user.getTasks();
		tasks.add(task);
		user.setTasks(tasks);
		return "assigned";
	}
}
