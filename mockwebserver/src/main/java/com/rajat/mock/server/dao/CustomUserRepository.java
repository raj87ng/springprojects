package com.rajat.mock.server.dao;

import com.rajat.mock.server.model.Role;
import com.rajat.mock.server.model.User;

import reactor.core.publisher.Mono;

public interface CustomUserRepository {
	Mono<User> changePassword (String userId, String newPassword);

    Mono<User> addNewRole (String userId, Role role);

    Mono<User> removeRole (String userId, String permission);

    Mono<Boolean> hasPermission (String userId, String permission);
}
