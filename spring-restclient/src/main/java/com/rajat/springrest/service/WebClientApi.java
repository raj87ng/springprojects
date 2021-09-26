package com.rajat.springrest.service;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.rajat.springrest.exception.BadRequestException;
import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;
import com.rajat.springrest.model.DealerResponse;

import reactor.core.publisher.Mono;

@Service
@ConditionalOnProperty(
	    value="rest.enable",
	    havingValue = "false")
public class WebClientApi implements ClientApis{

	private static final Logger LOG = LoggerFactory.getLogger(WebClientApi.class);
	
	 @Value("${base.url.mock.server}")                            //http://localhost:8383/mockserver
	 private String baseurl;
	  
	 @Value("${get.all.records}")                                // =/v1/records
	 private String getAllRecordsUrl;
	 
	 @Value("${get.delete.put.url}")                       // /v1/records/dealers/{dealerid}
	 private String getSingleRecordUrl;
	        
	 @Value("${post.dealer.record}")         // /v1/records/dealers
	 private String postRecord;
	 
	 private final WebClient webClient;
	 
	public WebClientApi() {
		LOG.debug("In WebClientApi constructor");
		this.webClient = WebClient.builder()
				                  .baseUrl("http://localhost:8383")
				                  .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				                  .filter(logRequest())
				                  .build();
				
	}

	private ExchangeFilterFunction logRequest() {
		return (request, nxt) ->{
			LOG.debug("WEBClient Request is {} and {} ",request.method(),request.url());
			request.headers().forEach((key,values) -> values.forEach(value -> LOG.debug("{} = {}",key , value)));
			return nxt.exchange(request);
		};
		
	}
	
	@Override
	public Optional<DealerDto> getAllDealerRecords() {
		LOG.debug("In getAllDealerRecords method of WebClient {} ",this.getClass());
		DealerResponse response = getAllRecordsWebClientCall();
		LOG.debug("In getAllDealerRecords method of {} with data {}",this.getClass(),response);
		return Optional.ofNullable(response.getClientData());
	}

	private DealerResponse getAllRecordsWebClientCall() {
		return this.webClient
				   .get()
				   .uri("/mockserver"+getAllRecordsUrl)
				   .retrieve()
				   .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new BadRequestException("Error in Get Records")))
				   .bodyToMono(DealerResponse.class)
				   .block();
	}

	@Override
	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		LOG.debug("In getSingleDealerRecord method {} ",this.getClass());
		DealerResponse response = getSingleRecordsRestCall(dealerId);
          LOG.debug("In getSingleDealerRecord method of {} with data {}",this.getClass(),response);
		
		return Optional.ofNullable(response.getClientData());
	}

	private DealerResponse getSingleRecordsRestCall(String dealerId) {
		return this.webClient
		   .get()
		   .uri(uriBuilder -> uriBuilder.path("/mockserver"+getSingleRecordUrl)
		   .build(dealerId))
		   .retrieve()
		   .onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new BadRequestException("Error in Get Records")))
		   .bodyToMono(DealerResponse.class)
		   .block();
	}

	@Override
	public boolean saveDealerRecord(DealerDetail dealer) {
		LOG.debug("In saveDealerRecord method {} ",this.getClass());
		String response = saveRecord(dealer);
		return (Objects.nonNull(response)?true:false);
	}

	private String saveRecord(DealerDetail dealer) {
		return this.webClient.post()
		.uri("/mockserver"+postRecord)
		.contentType(MediaType.APPLICATION_JSON)
		.accept(MediaType.APPLICATION_JSON)
		.body(Mono.just(dealer),DealerDetail.class)
		.retrieve()
		.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new BadRequestException("Error in Get Records")))
		   .bodyToMono(String.class)
		   .block();
	}

	@Override
	public boolean updateDealerRecord(@Valid DealerDetail dealer, String dealerid) {
		LOG.debug("In updateDealerRecord method {} ",this.getClass());
		String response = updateRecord(dealer,dealerid);
		return (Objects.nonNull(response)?true:false);
	}

	private String updateRecord(@Valid DealerDetail dealer, String dealerid) {
		return this.webClient.put()
				.uri(uriBuilder -> uriBuilder.path("/mockserver"+getSingleRecordUrl)
			    .build(dealerid))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(dealer),DealerDetail.class)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new BadRequestException("Error in Get Records")))
				   .bodyToMono(String.class)
				   .block();
	}

	@Override
	public boolean deleteDealerRecord(String dealerId) {
		LOG.debug("In deleteDealerRecord method {} ",this.getClass());
		String response = deleteRecord(dealerId);
		return (Objects.nonNull(response)?true:false);
	}

	private String deleteRecord(String dealerId) {
		return this.webClient.delete()
				.uri(uriBuilder -> uriBuilder.path("/mockserver"+getSingleRecordUrl)
			    .build(dealerId))
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, response -> Mono.error(new BadRequestException("Error in Get Records")))
				   .bodyToMono(String.class)
				   .block();
	}
}
