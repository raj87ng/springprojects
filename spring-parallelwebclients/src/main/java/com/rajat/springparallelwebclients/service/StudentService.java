package com.rajat.springparallelwebclients.service;


import com.rajat.springparallelwebclients.bean.StudentBean;
import com.rajat.springparallelwebclients.model.StudentDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface StudentService {
	
	Mono<StudentDto> createStudent(StudentBean e);
    
    Mono<StudentDto> findByStudentId(long id);
 
    Flux<List<StudentBean>> findByStudentName(String name);
 
    Flux<List<StudentBean>> findAllStudents();
 
    Mono<StudentDto> updateStudent(StudentBean e);
 
    Mono<StudentDto> deleteStudent(long id);
}
