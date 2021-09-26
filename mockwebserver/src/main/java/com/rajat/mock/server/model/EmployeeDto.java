package com.rajat.mock.server.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rajat.mock.server.annotations.OnlyStringAllowed;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto implements Serializable{


	private static final long serialVersionUID = 4613023485534307282L;
	
	@NotBlank
	@OnlyStringAllowed
	@Size(min=5, max=20, message="Name shall be in 5 to 20 length")
	private String name;
	
	
	private long salary;
	
	private String id;
}
