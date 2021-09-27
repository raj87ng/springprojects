package com.rajat.mock.server.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.rajat.mock.server.model.Person;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<Person, String>{

}
