package com.rajat.springrest.config;

import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestWebConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(RestWebConfiguration.class);
	
	@Bean
	public RestTemplate restTemplate() {
		LOG.debug("In {} , creating restTemplate ",this.getClass());
		HttpComponentsClientHttpRequestFactory httpCompClientFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.createDefault());
	    return new RestTemplate(httpCompClientFactory);
	}
	
}
