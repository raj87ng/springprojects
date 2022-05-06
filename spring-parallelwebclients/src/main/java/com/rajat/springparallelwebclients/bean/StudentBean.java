package com.rajat.springparallelwebclients.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.rajat.springparallelwebclients.annotations.OnlyStringAllowed;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentBean implements Serializable{


	private static final long serialVersionUID = 4613023485534307282L;
	
	@NotBlank
	@OnlyStringAllowed
	@Size(min=5, max=20, message="Name shall be in 5 to 20 length")
	private String name;
	@NotBlank
	@OnlyStringAllowed
	@Size(min=5, max=20, message="Last Name shall be in 5 to 20 length")
	private String lastName;
	private int age;
	@NotNull
	private long id;
}
