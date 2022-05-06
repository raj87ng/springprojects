package com.rajat.mock.server.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajat.mock.server.dao.EmployeeRepository;
import com.rajat.mock.server.entity.Employee;
import com.rajat.mock.server.model.EmployeeDto;
import com.rajat.mock.server.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Autowired
    EmployeeRepository empRepository;
	
	@Override
	public Mono<Employee> createEmployee(EmployeeDto e) {
		LOG.debug(" In createEmployee of {} with {} " , this.getClass(),e.toString());
		Employee emp = new Employee();
		emp.setName(e.getName());
		emp.setSalary(e.getSalary());
		emp.setCreatedOn(new Date());
		emp.setModifiedOn(new Date());
       return empRepository.save(emp);
	}

	@Override
	public Mono<Employee> findByEmployeeId(String id) {
		return empRepository.findById(id);
	}

	@Override
	public Flux<Employee> findByEmployeeName(String name) {
		return empRepository.findByName(name);
	}

	@Override
	public Flux<Employee> findAllEmpployees() {
		return empRepository.findAll();
	}

	@Override
	public Mono<Employee> updateEmployee(EmployeeDto e) {
		
		Employee emp = empRepository.findById(e.getId()).block();
		emp.setName(e.getName());
		emp.setSalary(e.getSalary());
		return empRepository.save(emp);
	}

	@Override
	public Mono<Void> deleteEmployee(String id) {
		return empRepository.deleteById(id);
	}

}
