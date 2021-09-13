package com.rajat.springrest.service;

import java.util.Optional;

import javax.validation.Valid;

import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;

public interface ClientApis {

	Optional<DealerDto> getAllDealerRecords();
	Optional<DealerDto> getSingleDealerRecord(String dealerId);
	boolean saveDealerRecord(DealerDetail dealer);
	boolean updateDealerRecord(@Valid DealerDetail dealer, String dealerid);
	boolean deleteDealerRecord(String dealerId);
}
