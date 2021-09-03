package com.rajat.mock.server.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"dealerCountry","dealerCity"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerInfo implements Serializable{

	private static final long serialVersionUID = 3362645996252161742L;
	
	@NotNull
	private int dealerId;
	@NotBlank
	private String dealerName;
	private String dealerCountry;
	private String dealerCity;
	

}
