package com.rajat.springparallelwebclients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rajat"})
public class SpringWebClientwebserverApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringWebClientwebserverApplication.class, args);
	}

}
