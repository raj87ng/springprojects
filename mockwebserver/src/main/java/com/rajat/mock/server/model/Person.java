package com.rajat.mock.server.model;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.rajat.mock.server.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"age"})
@NoArgsConstructor
@AllArgsConstructor
@Scope(scopeName = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonTypeInfo(use=com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS, defaultImpl = Employee.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="person")
public class Person {

	@Id
	String id;
    String firstName;
    String lastName;
    int age;
}
