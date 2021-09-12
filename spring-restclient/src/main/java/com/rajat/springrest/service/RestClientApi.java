package com.rajat.springrest.service;

import java.util.Optional;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;

@Service
@ConditionalOnProperty(
	    value="rest.enable",
	    havingValue = "true", 
	    matchIfMissing = true)
public class RestClientApi implements ClientApis{

	@Override
	public Optional<DealerDto> getAllDealerRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveDealerRecord(DealerDetail dealer) {
		// TODO Auto-generated method stub
		return false;
	}

}
