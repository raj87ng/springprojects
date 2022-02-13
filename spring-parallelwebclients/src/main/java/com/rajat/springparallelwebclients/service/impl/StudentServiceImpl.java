package com.rajat.springparallelwebclients.service.impl;


import com.rajat.springparallelwebclients.bean.StudentBean;
import com.rajat.springparallelwebclients.exception.BadRequestException;
import com.rajat.springparallelwebclients.model.StudentDto;
import com.rajat.springparallelwebclients.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

	private static final Logger LOG = LoggerFactory.getLogger(StudentServiceImpl.class);
	
	private static ConcurrentHashMap<String, StudentBean> studentMap = new ConcurrentHashMap<>();
	
	@Override
	public Mono<StudentDto> createStudent(StudentBean e) {
		LOG.debug(" In createEmployee of {} with {} " , this.getClass(),e.toString());
		if(Objects.nonNull(studentMap.get(String.valueOf(e.getId())))){
			throw new BadRequestException("Record with ID :"+e.getId()+ " already exists");
		}
         studentMap.put(String.valueOf(e.getId()),e);
		 StudentDto sdto = new StudentDto();
		 sdto.setName(e.getName());
       return Mono.justOrEmpty(sdto);
	}

	@Override
	public Mono<StudentDto> findByStudentId(long id) {
		LOG.debug(" In findByStudentId of {} with ID : {} " , this.getClass(),id);
		if(Objects.isNull(studentMap.get(String.valueOf(id)))){
			throw new BadRequestException("Record with ID :"+id+ " not exists");
		}
		StudentBean bean = studentMap.get(String.valueOf(id));

		StudentDto sdto = new StudentDto();
		sdto.setName(bean.getName());
		sdto.setAge(bean.getAge());
		sdto.setId(bean.getId());
		return Mono.justOrEmpty(sdto);

	}

	@Override
	public Flux<List<StudentBean>> findByStudentName(String name) {
		LOG.debug(" In findByStudentName of {} with name : {} " , this.getClass(),name);
		List<StudentBean> studentB = studentMap.values()
				                               .stream()
				                                .filter(x -> name.equalsIgnoreCase(x.getName()))
				                                 .collect(Collectors.toList());
		if(Objects.isNull(studentB)){
			throw new BadRequestException("Records with name :"+ name+ " not exists");
		}
     	return Flux.just(studentB);
	}


	@Override
	public Flux<List<StudentBean>> findAllStudents() {
		LOG.debug(" In findAllStudents of {}  " , this.getClass());
		List<StudentBean> studentB = studentMap.values()
				.stream()
				.collect(Collectors.toList());
		if(Objects.isNull(studentB)){
			throw new BadRequestException("Records not exists");
		}
		return Flux.just(studentB);
	}

	@Override
	public Mono<StudentDto> updateStudent(StudentBean e) {
		LOG.debug(" In updateStudent of {} with {} " , this.getClass(),e.toString());
		if(Objects.isNull(studentMap.get(String.valueOf(e.getId())))){
			throw new BadRequestException("Record with ID :"+e.getId()+ " not exists to update");
		}
		studentMap.put(String.valueOf(e.getId()),e);
		StudentDto sdto = new StudentDto();
		sdto.setName(e.getName());
		return Mono.justOrEmpty(sdto);
	}

	@Override
	public Mono<StudentDto> deleteStudent(long id) {
		LOG.debug(" In deleteStudent of {} with ID : {} " , this.getClass(),id);
		if(Objects.isNull(studentMap.get(String.valueOf(id)))){
			throw new BadRequestException("Record with ID :"+id+ " not exists to delete");
		}
		StudentBean bean = studentMap.get(String.valueOf(id));
		studentMap.remove(String.valueOf(id));
		StudentDto sdto = new StudentDto();
		sdto.setName(bean.getName());
		sdto.setId(bean.getId());
		return Mono.justOrEmpty(sdto);
	}

}
