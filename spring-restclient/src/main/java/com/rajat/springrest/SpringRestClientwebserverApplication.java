package com.rajat.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rajat.springrest"})
public class SpringRestClientwebserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRestClientwebserverApplication.class, args);
	}

}
