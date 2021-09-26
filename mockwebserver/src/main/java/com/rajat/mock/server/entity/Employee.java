package com.rajat.mock.server.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"salary","createdOn","modifiedOn"})
@Scope(scopeName = "request",proxyMode = ScopedProxyMode.TARGET_CLASS)
@JsonTypeInfo(use=com.fasterxml.jackson.annotation.JsonTypeInfo.Id.CLASS, defaultImpl = Employee.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "employeeInfo")
public class Employee {

	@Id
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	private long salary;
	@NotBlank
	private Date createdOn;
	private Date modifiedOn;
}
