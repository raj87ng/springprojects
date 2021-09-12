package com.rajat.springrest.service;

import java.util.Optional;

import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;

public interface ClientApis {

	Optional<DealerDto> getAllDealerRecords();
	Optional<DealerDto> getSingleDealerRecord(String dealerId);
	boolean saveDealerRecord(DealerDetail dealer);
}
