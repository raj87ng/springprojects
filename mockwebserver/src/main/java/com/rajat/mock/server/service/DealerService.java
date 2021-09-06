package com.rajat.mock.server.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.rajat.mock.server.entity.DealerInfo;
import com.rajat.mock.server.model.DealerDetail;
import com.rajat.mock.server.model.DealerDetails;
import com.rajat.mock.server.model.DealerDto;

@Service
public class DealerService {
	private static final Logger LOG = LoggerFactory.getLogger(DealerService.class);
	private static final Map<String,DealerInfo> dealerData = new ConcurrentHashMap<>();
	
	public Optional<DealerDto> getAllDealerRecords() {
		LOG.debug("Inside getAllDealerRecords method");
		List<DealerInfo> dealersInfo = dealerData.values().stream().collect(Collectors.toList());
		
		List<DealerDetails> dealersInfo1 = new ArrayList<>();
		dealersInfo.forEach(x -> {
			DealerDetails details = new DealerDetails();
			details.setDealerId(x.getDealerId());
			details.setDealerName(x.getDealerName());
			details.setDealerCity(x.getDealerCity());
			details.setDealerCountry(x.getDealerCountry());
			dealersInfo1.add(details);
		});
		DealerDto dto = new DealerDto();
		dto.setDealerDetails(dealersInfo1);
		LOG.debug("getAllDealerRecords fetched successfully");
		return Optional.ofNullable(dto);
	}

	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		LOG.debug("Inside getSingleDealerRecord method");
		DealerInfo dealerInfo = null;
		if(Objects.nonNull(dealerData.get(dealerId))) {
			dealerInfo = dealerData.get(dealerId);
			
		}else {
			return Optional.empty();
		}
		List<DealerDetails> dealersInfo1 = new ArrayList<>();
		DealerDetails details = new DealerDetails();
		details.setDealerId(dealerInfo.getDealerId());
		details.setDealerName(dealerInfo.getDealerName());
		details.setDealerCity(dealerInfo.getDealerCity());
		details.setDealerCountry(dealerInfo.getDealerCountry());
		dealersInfo1.add(details);
		DealerDto dto = new DealerDto();
		dto.setDealerDetails(dealersInfo1);
		return Optional.ofNullable(dto);
	}
	
	public boolean saveDealerRecord(DealerDetail dealer) {
		int records = dealerData.size();
		DealerInfo dlr = new DealerInfo();
		dlr.setDealerId(records);
		dlr.setDealerName(dealer.getDealerName());
		dlr.setDealerCountry(dealer.getDealerCountry());
		dlr.setDealerCity(dealer.getDealerCity());
		dealerData.put(String.valueOf(records), dlr);
		return true;
	}
	
	
	
	@PostConstruct
	public void init() {
		LOG.debug("In Post Construct Init method");
		addDealerDetails();
		LOG.debug("Exit Post Construct Init method");
	}
	
	/**
	 * Add Records
	 */
	private void addDealerDetails() {
		DealerInfo d1 = new DealerInfo();
		d1.setDealerId(1);
		d1.setDealerName("ABC");
		d1.setDealerCountry("INDIA");
		d1.setDealerCity("GURUGRAM");
		dealerData.put("1", d1);
		DealerInfo d2 = new DealerInfo();
		d2.setDealerId(2);
		d2.setDealerName("GTY");
		d2.setDealerCountry("INDIA");
		d2.setDealerCity("DELHI");
		dealerData.put("2", d2);
		DealerInfo d3 = new DealerInfo();
		d3.setDealerId(3);
		d3.setDealerName("JUI");
		d3.setDealerCountry("INDIA");
		d3.setDealerCity("BANGLORE");
		dealerData.put("3", d3);
		DealerInfo d4 = new DealerInfo();
		d4.setDealerId(4);
		d4.setDealerName("DLM");
		d4.setDealerCountry("INDIA");
		d4.setDealerCity("NOIDA");
		dealerData.put("4", d4);
		LOG.debug("Add Data in MAP {}",dealerData.toString());
	}
	
	
}
