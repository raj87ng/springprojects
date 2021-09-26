package com.rajat.mock.server.rest;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.mock.server.annotations.OnlyStringAllowed;
import com.rajat.mock.server.constant.Constants;
import com.rajat.mock.server.entity.Employee;
import com.rajat.mock.server.model.EmployeeDto;
import com.rajat.mock.server.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
public class EmployeeController {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService empService;
	
	@PostMapping(value = Constants.ADD_EMPLOYEE , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Employee> addEmployee(@RequestBody @Valid EmployeeDto employee) {
		LOG.debug(" In addEmployee of {} with {} " , this.getClass(),employee.toString());
		return empService.createEmployee(employee);
    }

    @GetMapping(value = Constants.GET_ALL_EMPLOYEES, produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Employee> findAllEmployees() {
    	LOG.debug(" In findAllEmployees of {}" , this.getClass());
        return empService.findAllEmpployees();
    }

    @GetMapping(value = Constants.GET_SINGLE_EMPLOYEE)
    public ResponseEntity<Mono<Employee>> findEmployeeById(@PathVariable("employeeId") String employeeId) {
    	LOG.debug(" In findEmployeeById of {} with employeeId {} " , this.getClass(),employeeId);
        Mono<Employee> employee = empService.findByEmployeeId(employeeId);
        return new ResponseEntity<Mono<Employee>>(employee, employee != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = Constants.GET_EMPLOYEE_BY_NAME)
    public Flux<Employee> findByName(@PathVariable("name") @OnlyStringAllowed String name) {
    	LOG.debug(" In findByName of {} with employee name {} " , this.getClass(),name);
        return empService.findByEmployeeName(name);
    }

    @PutMapping(value = Constants.UPDATE_EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public Mono<Employee> updateEmployee(@RequestBody @Valid EmployeeDto employee) {
    	LOG.debug(" In addEmployee of {} with {} " , this.getClass(),employee.toString());
        return empService.updateEmployee(employee);
    }

    @DeleteMapping(value = Constants.DELETE_EMPLOYEE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable("employeeId") String employeeId) {
    	LOG.debug(" In deleteEmployee of {} with employeeId{} " , this.getClass(),employeeId);
    	empService.deleteEmployee(employeeId).subscribe();
    }

}
