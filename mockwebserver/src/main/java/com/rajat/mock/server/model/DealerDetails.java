package com.rajat.mock.server.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;



@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerDetails implements Serializable{

	private static final long serialVersionUID = -5866423821254680603L;

	@JsonProperty(value = "dealerId")
	@JsonAlias(value = {"dealerId"})
	private int dealerId;
	
	@JsonProperty(value = "dealerName")
	@JsonAlias(value = {"dealerName"})
	private String dealerName;
	
	@JsonProperty(value = "dealerCountry")
	@JsonAlias(value = {"dealerCountry"})
	private String dealerCountry;
	
	@JsonProperty(value = "dealerCity")
	@JsonAlias(value = {"dealerCity"})
	private String dealerCity;
	
}
