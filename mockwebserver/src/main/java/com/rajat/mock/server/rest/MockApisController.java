package com.rajat.mock.server.rest;

import java.util.Optional;

import javax.validation.constraints.Max;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.mock.server.annotations.OnlyDigitsAllowed;
import com.rajat.mock.server.model.DealerDto;
import com.rajat.mock.server.response.ClientResponse;
import com.rajat.mock.server.service.DealerService;


@RestController
@Validated
public class MockApisController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockApisController.class);
	
	@Autowired
	private DealerService dealerService;
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/v1/records", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<DealerDto>> getAllRecords(){
		LOG.debug("Inside {} , getAllRecords method",this.getClass() );
		Optional<DealerDto> dealerRecords = dealerService.getAllDealerRecords();
		@SuppressWarnings("rawtypes")
		ClientResponse clientResponse = null;
		if(dealerRecords.isPresent()) {
			clientResponse=  new ClientResponse.ResponseBuilder<>("dealer-1", "SUCCESS").withClientData(dealerRecords).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}else {
			clientResponse=  new ClientResponse.ResponseBuilder<>("dealer-2", "No Dealer Data Found").build();
			return new ResponseEntity<>(clientResponse,HttpStatus.NO_CONTENT);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = "/v1/records/notfound", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse> getAllRecordsWithNoRecords(){
		ClientResponse clientResponse = null;
		clientResponse=  new ClientResponse.ResponseBuilder<>("dealer-2", "No Dealer Data Found").build();
		return new ResponseEntity<>(clientResponse,HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(value = "/v1/records/dealers/{dealerid}", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<DealerDto>> getSingleRecord(@PathVariable @OnlyDigitsAllowed @Max(value=10,message = "Not more than 10") String dealerCode){
		
		return new ResponseEntity<T>(null,HttpStatus.OK);
	}

}
