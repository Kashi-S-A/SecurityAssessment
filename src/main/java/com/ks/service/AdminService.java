package com.ks.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ks.entity.Role;
import com.ks.entity.User;
import com.ks.exception.DuplicateEmailException;
import com.ks.exception.UnauthorizedAccessException;
import com.ks.exception.UserNotFound;
import com.ks.repository.RoleRepository;
import com.ks.repository.UserRepository;

@Service
public class AdminService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepository;

	public String saveUser(User user) {
		User byEmail = repository.findByEmail(user.getEmail());
		if (byEmail != null) {
			throw new DuplicateEmailException();
		} else {
			return "User Registered";
		}
	}

	public String updateUser(Long id, User user) {
		User dbUser = repository.findById(id).orElseThrow(() -> new UserNotFound());

		BeanUtils.copyProperties(user, dbUser);
		dbUser.setId(id);

		repository.save(dbUser);
		return "updated";
	}

	public User getUser(Long id) {
		User dbUser = repository.findById(id).orElseThrow(() -> new UserNotFound());
		return dbUser;
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public String assignRole(Long userId, String roleName) {
		User dbUser = repository.findById(userId).orElseThrow(() -> new UserNotFound());
		Role role = roleRepository.findByName(roleName);
		List<Role> roles = dbUser.getRoles();
		roles.add(role);
		dbUser.setRoles(roles);
		repository.save(dbUser);
		return "Role assigned";
	}
}
