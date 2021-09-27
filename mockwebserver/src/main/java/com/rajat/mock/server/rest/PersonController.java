package com.rajat.mock.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rajat.mock.server.model.Person;
import com.rajat.mock.server.service.PersonService;

import reactor.core.publisher.Mono;

@Component
public class PersonController {

	private final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	private PersonService service;

    public Mono<ServerResponse> createPerson(ServerRequest request){
        Mono<Person> payload = request.bodyToMono(Person.class);
        return ServerResponse.ok().contentType(JSON).body(payload.flatMap(service::create), Person.class);
    }
    
    public Mono<ServerResponse> remove(ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(JSON).body(service.remove(id), Void.class);
    }

    public Mono<ServerResponse> update (ServerRequest request){
        Mono<Person> payload = request.bodyToMono(Person.class);
        return ServerResponse.ok().contentType(JSON).body(payload.flatMap(service::update), Void.class);
    }

    public Mono<ServerResponse> findById (ServerRequest request){
        String id = request.pathVariable("id");
        return ServerResponse.ok().contentType(JSON).body(service.findById(id), Person.class);
    }
	
}
