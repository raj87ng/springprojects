package com.rajat.mock.server.webcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.rajat.mock.server.config.WebRouterConfig;
import com.rajat.mock.server.model.Person;
import com.rajat.mock.server.rest.PersonController;
import com.rajat.mock.server.service.PersonService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {WebRouterConfig.class, PersonController.class})
@WebFluxTest
public class PersonApiTests {

	@Autowired
    private WebTestClient client;

    @MockBean
    private PersonService service;

    @Test
    void findByIdTest(){
        Person person = new Person("id", "ABC", "XYZ", 27);
        String id = "id";
        Mono<Person> source = Mono.just(person);
        when(service.findById(id)).thenReturn(source);
        client.get().uri("/v1/get/person/{id}", id).exchange()
            .expectBody(Person.class)
            .value(persn -> assertThat(persn).isNotNull().hasNoNullFieldsOrProperties());
    }

    @Test
    void createTest(){
        Person person = new Person("id", "ABC", "XYZ", 27);
        Mono<Person> source = Mono.just(person);
        when(service.create(any(Person.class))).thenReturn(source);
        client.post()
            .uri("/v1/add/person")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(person))
            .exchange().expectBody(Person.class).value(p -> assertThat(p)
                .hasFieldOrPropertyWithValue("firstName", person.getFirstName())
                .hasFieldOrPropertyWithValue("lastName", person.getLastName())
                .hasFieldOrPropertyWithValue("id", person.getId())
                .hasFieldOrPropertyWithValue("age", person.getAge()));
    }

    @Test
    void deleteTest(){
        // basic assertions
        when(service.remove(anyString())).thenReturn(Mono.empty());
        client.delete().uri("/v1/delete/person/{id}", "id").exchange().expectStatus().isOk();
    }
}
