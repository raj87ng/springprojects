package com.rajat.mock.server.service;

import com.rajat.mock.server.model.User;

import reactor.core.publisher.Mono;

public interface UserService {

	Mono<User> createUser(User user);

    Mono<Void> removeUser(String userId);

    Mono<User> findOne (String userId);

    Mono<User> changePassword (String userId, String password);
}
