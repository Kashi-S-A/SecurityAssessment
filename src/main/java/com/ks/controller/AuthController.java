package com.ks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ks.entity.User;
import com.ks.jwt.JwtService;
import com.ks.repository.UserRepository;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<String> save(@RequestBody User user) {
		
		String encodedPwd = passwordEncoder.encode(user.getPassword());

		user.setPassword(encodedPwd);
		
		userRepository.save(user);
		
		return new ResponseEntity<String>("Registered Successfull", HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		
		try {
			Authentication authenticate = authenticationManager.authenticate(token);
			if (authenticate.isAuthenticated()) {
				
				String jwtToken = jwtService.generateToken(user.getEmail());
				
				return new ResponseEntity<String>(jwtToken, HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
	
}
