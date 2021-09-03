package com.rajat.mock.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerDto implements Serializable{

	private static final long serialVersionUID = 5550488260942694004L;

	
	@JsonProperty(value = "dealerDetails")
	@JsonAlias(value = {"dealerDetails"})
	private List<DealerDetails> dealerDetails;
}
