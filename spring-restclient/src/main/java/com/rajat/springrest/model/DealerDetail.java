package com.rajat.springrest.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rajat.springrest.annotations.OnlyStringAllowed;

import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerDetail implements Serializable{

	private static final long serialVersionUID = -5866423821254680603L;

	
	@JsonProperty(value = "dealerName")
	@JsonAlias(value = {"dealerName"})
	@NotBlank
	@OnlyStringAllowed
	@Size(min=5, max=20, message="Name shall be in 5 to 20 length")
	private String dealerName;
	
	@JsonProperty(value = "dealerCountry")
	@JsonAlias(value = {"dealerCountry"})
	@NotBlank
	@OnlyStringAllowed
	@Size(min=2, max=10, message="Country shall be in 2 to 10 length")
	private String dealerCountry;
	
	@JsonProperty(value = "dealerCity")
	@JsonAlias(value = {"dealerCity"})
	@NotBlank
	@OnlyStringAllowed
	@Size(min=2, max=20, message="City shall be in 2 to 20 length")
	private String dealerCity;
	
}
