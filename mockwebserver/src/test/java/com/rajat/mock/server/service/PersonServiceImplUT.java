package com.rajat.mock.server.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rajat.mock.server.dao.PersonRepository;
import com.rajat.mock.server.model.Person;
import com.rajat.mock.server.service.impl.PersonServiceImpl;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class PersonServiceImplUT {

	
	 @InjectMocks 
	 private PersonServiceImpl service;
	    
	 @Mock 
	 private PersonRepository repository;

	    @Test
	    void createPersonTest(){
	        Person data = new Person("id", "ABC", "XYZ", 29);
	        Mono<Person> source = Mono.just(data);
	        when(repository.save(any(Person.class))).thenReturn(source);
	        StepVerifier.create(service.create(data)).assertNext(person -> 
	            assertThat(person).hasNoNullFieldsOrProperties()).verifyComplete();
	    }

	    @Test
	    void findByIdTest(){
	        String id = "id";
	        Person data = new Person("id", "ABC", "XYZ", 29);
	        Mono<Person> source = Mono.just(data);
	        when(repository.findById(id)).thenReturn(source);
	        StepVerifier.create(service.findById(id)).assertNext(person -> 
	            assertThat(person).hasNoNullFieldsOrProperties()).verifyComplete();
	    }

	    @Test
	    void removeTest() {
	        String id = "id";
	        when(repository.deleteById(anyString())).thenReturn(Mono.empty());
	        StepVerifier.create(service.remove(id)).verifyComplete();
	    }
	    
	    
}
