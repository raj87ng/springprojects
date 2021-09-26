package com.rajat.mock.server.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.rajat.mock.server.entity.Employee;

import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String>{

	@Query("{ 'name': ?0 }")
    Flux<Employee> findByName(final String name);

	
}
