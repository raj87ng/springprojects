package com.rajat.mock.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.rajat.mock.server.constant.Constants;
import com.rajat.mock.server.rest.PersonController;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class WebRouterConfig {

	private final MediaType JSON = MediaType.APPLICATION_JSON;
	
	 @Bean
	 public RouterFunction<ServerResponse> personEndpoint(PersonController handler){
	    	return RouterFunctions
	                .route(POST(Constants.ADD_PERSON).and(accept(JSON)), handler::createPerson)
	                .andRoute(GET(Constants.GET_PERSON).and(accept(JSON)), handler::findById)
	                .andRoute(DELETE(Constants.DELETE_PERSON).and(accept(JSON)), handler::remove)
	                .andRoute(PUT(Constants.UPDATE_PERSON).and(accept(JSON)), handler::update);
	    }
}
