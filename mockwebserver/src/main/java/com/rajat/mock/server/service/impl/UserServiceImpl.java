package com.rajat.mock.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajat.mock.server.dao.UserRepository;
import com.rajat.mock.server.model.User;
import com.rajat.mock.server.service.UserService;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Mono<User> createUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public Mono<Void> removeUser(String userId) {
		return this.userRepository.deleteById(userId);
	}

	@Override
	public Mono<User> findOne(String userId) {
		return this.userRepository.findById(userId);
	}

	@Override
	public Mono<User> changePassword(String userId, String password) {
		return this.userRepository.changePassword(userId, password);
	}

}
