package com.rajat.springrest.service;


import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;



@Service
public class DealerService {
	private static final Logger LOG = LoggerFactory.getLogger(DealerService.class);
	
	@Autowired
	private ClientApis clientApi;
	
	
	public Optional<DealerDto> getAllDealerRecords() {
		LOG.debug("Inside getAllDealerRecords method");
		return clientApi.getAllDealerRecords();
	}

	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		LOG.debug("Inside getSingleDealerRecord method");
		return clientApi.getSingleDealerRecord(dealerId);
	}
	
	public boolean saveDealerRecord(DealerDetail dealer) {
		return clientApi.saveDealerRecord(dealer);
	}
	
	public boolean updateDealerRecord(@Valid DealerDetail dealer, String dealerid) {
		return clientApi.updateDealerRecord(dealer, dealerid);
	}
	
	public boolean deleteDealerRecord(String dealerId) {
		
		return true;
	}
	
	
	
	
	
	
	
}
