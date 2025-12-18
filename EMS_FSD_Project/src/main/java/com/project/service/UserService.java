package com.project.service;

import org.springframework.stereotype.Service;

import com.project.model.User;
import com.project.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public User findUsernameAndPassword(String username,String password) {
		return repo.findByUsernameAndPassword(username, password);
	}
}
