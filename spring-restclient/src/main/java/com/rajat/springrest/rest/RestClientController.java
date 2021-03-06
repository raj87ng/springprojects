package com.rajat.springrest.rest;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rajat.springrest.annotations.OnlyDigitsAllowed;
import com.rajat.springrest.constant.Constants;
import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;
import com.rajat.springrest.response.ClientResponse;
import com.rajat.springrest.service.DealerService;



@RestController
@Validated
public class RestClientController {

private static final Logger LOG = LoggerFactory.getLogger(RestClientController.class);
	
	@Autowired
	private DealerService dealerService;
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = Constants.GET_ALL_RECORDS, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<DealerDto>> getAllRecords(){
		LOG.debug("Inside {} , getAllRecords method",this.getClass() );
		Optional<DealerDto> dealerRecords = dealerService.getAllDealerRecords();
		@SuppressWarnings("rawtypes")
		ClientResponse clientResponse = null;
		if(dealerRecords.isPresent()) {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_1, Constants.SUCCESS).withClientData(dealerRecords).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}else {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_2, Constants.NO_DEALER_FOUND).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	/**
	 * OUTPUT : 
	 * {
    "clientCode": "dealer-1",
    "clientMessage": "SUCCESS",
    "clientData": {
        "dealerDetails": [
            {
                "dealerId": 1,
                "dealerName": "ABC",
                "dealerCountry": "INDIA",
                "dealerCity": "GURUGRAM"
            },
            {
                "dealerId": 2,
                "dealerName": "GTY",
                "dealerCountry": "INDIA",
                "dealerCity": "DELHI"
            },
            {
                "dealerId": 3,
                "dealerName": "JUI",
                "dealerCountry": "INDIA",
                "dealerCity": "BANGLORE"
            },
            {
                "dealerId": 4,
                "dealerName": "DLM",
                "dealerCountry": "INDIA",
                "dealerCity": "NOIDA"
            },
            {
                "dealerId": 5,
                "dealerName": "SAGARRR",
                "dealerCountry": "INDIA",
                "dealerCity": "GAZIABAD"
            },
            {
                "dealerId": 6,
                "dealerName": "RAJAT",
                "dealerCountry": "INDIA",
                "dealerCity": "XSSSS"
            }
        ]
    }
}
	 * @return
	 */
	
	@SuppressWarnings("rawtypes")
	@GetMapping(value = Constants.GET_RECORD_NOTFOUND, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse> getAllRecordsWithNoRecords(){
		ClientResponse clientResponse = null;
		clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_2, Constants.NO_DEALER_FOUND).build();
		return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@GetMapping(value = Constants.GETORDELETE_DEALER_RECORD, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<DealerDto>> getSingleRecord(@PathVariable @OnlyDigitsAllowed String dealerid){
		Optional<DealerDto> dealerRecords = dealerService.getSingleDealerRecord(dealerid);
		ClientResponse clientResponse = null;
		if(dealerRecords.isPresent()) {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_1, Constants.SUCCESS).withClientData(dealerRecords).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}else {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_2, Constants.NO_DEALER_FOUND).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * OutPut :
	 * 
	 * {
    "clientCode": "dealer-1",
    "clientMessage": "SUCCESS",
    "clientData": {
        "dealerDetails": [
            {
                "dealerId": 6,
                "dealerName": "RAJAT",
                "dealerCountry": "INDIA",
                "dealerCity": "XSSSSS"
            }
        ]
    }
}
	 * @param dealer
	 * @return
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping(value = Constants.POST_DEALER_RECORD, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<String>> saveDealerRecord(@RequestBody @Valid DealerDetail dealer){
		ClientResponse clientResponse = null;
		if(dealerService.saveDealerRecord(dealer)) {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_1, Constants.SUCCESS).withClientData(Constants.SAVED).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}
		else {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_3, Constants.NOT_SAVED).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
	/*
	 * Input :
	 * {
                "dealerName": "RAJAT",
                "dealerCountry": "INDIA",
                "dealerCity": "NARAINGARH"
            }
            
       OutPut : 
       {
    "clientCode": "dealer-1",
    "clientMessage": "SUCCESS",
    "clientData": "Saved"
}     
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PutMapping(value = Constants.GETORDELETE_DEALER_RECORD, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<String>> updateDealerRecord(@PathVariable @OnlyDigitsAllowed String dealerid, @RequestBody @Valid DealerDetail dealer){
		ClientResponse clientResponse = null;
		if(dealerService.updateDealerRecord(dealer,dealerid)) {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_1, Constants.SUCCESS).withClientData(Constants.UPDATED).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}
		else {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_3, Constants.NOT_SAVED).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping(value = Constants.GETORDELETE_DEALER_RECORD, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<ClientResponse<String>> deleteDealerRecord(@PathVariable @OnlyDigitsAllowed String dealerid){
		ClientResponse clientResponse = null;
		if(dealerService.deleteDealerRecord(dealerid)) {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_1, Constants.SUCCESS).withClientData(Constants.RECORD_DELETED).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.OK);
		}
		else {
			clientResponse=  new ClientResponse.ResponseBuilder<>(Constants.DEALER_4, Constants.RECORD_NOT_EXISTS).build();
			return new ResponseEntity<>(clientResponse,HttpStatus.BAD_REQUEST);
		}
	}
}
