package com.rajat.mock.server.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.rajat.mock.server.model.User;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>, CustomUserRepository{
    // derived query method
    Mono<User> findByEmail (String email);

}
