package com.rajat.mock.server.service;

import com.rajat.mock.server.model.Person;

import reactor.core.publisher.Mono;

public interface PersonService {

	Mono<Person> create (Person person);

    Mono<Void> remove (String id);

    Mono<Person> findById (String id);

    Mono<Void> update (Person person);
}
