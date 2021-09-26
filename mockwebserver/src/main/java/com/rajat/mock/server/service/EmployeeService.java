package com.rajat.mock.server.service;

import com.rajat.mock.server.entity.Employee;
import com.rajat.mock.server.model.EmployeeDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	
	Mono<Employee> createEmployee(EmployeeDto e);
    
    Mono<Employee> findByEmployeeId(String id);
 
    Flux<Employee> findByEmployeeName(String name);
 
    Flux<Employee> findAllEmpployees();
 
    Mono<Employee> updateEmployee(EmployeeDto e);
 
    Mono<Void> deleteEmployee(String id);
}
