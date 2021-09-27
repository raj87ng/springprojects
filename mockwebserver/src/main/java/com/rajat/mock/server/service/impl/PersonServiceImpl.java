package com.rajat.mock.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajat.mock.server.dao.PersonRepository;
import com.rajat.mock.server.model.Person;
import com.rajat.mock.server.service.PersonService;

import reactor.core.publisher.Mono;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Mono<Person> create(Person person) {
		return this.personRepository.save(person);
	}

	@Override
	public Mono<Void> remove(String id) {
		return this.personRepository.deleteById(id);
	}

	@Override
	public Mono<Person> findById(String id) {
		return this.personRepository.findById(id);
	}

	@Override
	public Mono<Void> update(Person person) {
		return this.personRepository.save(person).then();
	}

}
