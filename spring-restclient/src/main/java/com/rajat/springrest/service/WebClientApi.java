package com.rajat.springrest.service;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;

@Service
@ConditionalOnProperty(
	    value="rest.enable",
	    havingValue = "false")
public class WebClientApi implements ClientApis{

	private static final Logger LOG = LoggerFactory.getLogger(WebClientApi.class);
	
	public WebClientApi() {
		LOG.debug("In WebClientApi constructor");
	}

	@Override
	public Optional<DealerDto> getAllDealerRecords() {
		LOG.debug("In getAllDealerRecords method of {} ",this.getClass());
		return null;
	}

	@Override
	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		LOG.debug("In getSingleDealerRecord method {} ",this.getClass());
		return null;
	}

	@Override
	public boolean saveDealerRecord(DealerDetail dealer) {
		LOG.debug("In saveDealerRecord method {} ",this.getClass());
		return false;
	}

	@Override
	public boolean updateDealerRecord(@Valid DealerDetail dealer, String dealerid) {
		LOG.debug("In updateDealerRecord method {} ",this.getClass());
		return false;
	}

	@Override
	public boolean deleteDealerRecord(String dealerId) {
		LOG.debug("In deleteDealerRecord method {} ",this.getClass());
		return false;
	}
}
