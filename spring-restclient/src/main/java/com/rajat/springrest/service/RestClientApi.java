package com.rajat.springrest.service;

import java.awt.SecondaryLoop;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rajat.springrest.exception.BadRequestException;
import com.rajat.springrest.model.DealerDetail;
import com.rajat.springrest.model.DealerDto;
import com.rajat.springrest.model.DealerResponse;

@Service
@ConditionalOnProperty(
	    value="rest.enable",
	    havingValue = "true", 
	    matchIfMissing = true)
public class RestClientApi implements ClientApis{
	
	 @Value("${base.url.mock.server}")                            //http://localhost:8383/mockserver
	 private String baseurl;
	  
	 @Value("${get.all.records}")                                // =/v1/records
	 private String getAllRecordsUrl;
	 
	 @Value("${get.delete.put.url}")                       // /v1/records/dealers/{dealerid}
	 private String getSingleRecordUrl;
	        
	 @Value("${post.dealer.record}")         // /v1/records/dealers
	 private String postRecord;
		
	 @Autowired
	 private RestTemplate restTemplate;

	private static final Logger LOG = LoggerFactory.getLogger(RestClientApi.class);
	public RestClientApi() {
		LOG.debug("In RestClientApi constructor");
	}
	
	@Override
	public Optional<DealerDto> getAllDealerRecords() {
		LOG.debug("In getAllDealerRecords method of {} ",this.getClass());
		ResponseEntity<DealerResponse> response = getAllRecordsRestCall();
		DealerResponse dlrResponse = response.getBody();
		LOG.debug("In getAllDealerRecords method of {} with data {}",this.getClass(),dlrResponse);
		
		return Optional.ofNullable(dlrResponse.getClientData());
	}

	@Override
	public Optional<DealerDto> getSingleDealerRecord(String dealerId) {
		LOG.debug("In getSingleDealerRecord method of {} ",this.getClass());
		ResponseEntity<DealerResponse> response = getSingleRecordsRestCall(dealerId);
		DealerResponse dlrResponse = response.getBody();
          LOG.debug("In getAllDealerRecords method of {} with data {}",this.getClass(),dlrResponse);
		
		return Optional.ofNullable(dlrResponse.getClientData());
	}

	@Override
	public boolean saveDealerRecord(DealerDetail dealer) {
		LOG.debug("In saveDealerRecord method of {} ",this.getClass());
		ResponseEntity<String> response = saveRecord(dealer);
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			return false;
		}
		return true;
	}

	private ResponseEntity<String> saveRecord(DealerDetail dealer) {
		try {
			String url = baseurl+postRecord;
			HttpEntity<DealerDetail> httpEntity = new HttpEntity<>(dealer, createHeaders());
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
			return response;
			}
			catch(HttpClientErrorException hce) {
				if(hce.getStatusCode()==HttpStatus.UNAUTHORIZED) {
					throw new BadRequestException("Unauthorized Access");
				}else if(hce.getStatusCode()==HttpStatus.FORBIDDEN) {
					throw new BadRequestException("Forbidden Access");
				}else {
					throw new BadRequestException("Failure");
				}
			}catch(Exception e) {
				throw new BadRequestException("Generic Failure");
			}
		}
	

	@Override
	public boolean updateDealerRecord(@Valid DealerDetail dealer, String dealerid) {
		LOG.debug("In updateDealerRecord method of {} ",this.getClass());
		ResponseEntity<String> response = updateRecord(dealer,dealerid);
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			return false;
		}
		return true;
	}

	private ResponseEntity<String> updateRecord(@Valid DealerDetail dealer, String dealerid) {
		try {
			String url = baseurl+getSingleRecordUrl;
			// Second way to add PAth Variables and QueryParams
			/**
			 * Map<String, String> params = new HashMap<String, String>();
               params.put("dealerid", dealerid);
               URI uri = UriComponentsBuilder.fromUriString(url)
                  .buildAndExpand(params)
                  .toUri();
                uri = UriComponentsBuilder
		        .fromUri(uri)
		        .queryParam("name", "myName")
		        .build()
		        .toUri();
			 */
			
		    URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(dealerid).toUri();
			LOG.debug("URI is  method of {} and {}",uri.toString(),uri.toURL().toString());
			HttpEntity<DealerDetail> httpEntity = new HttpEntity<>(dealer, createHeaders());
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, String.class);
			return response;
			}
			catch(HttpClientErrorException hce) {
				if(hce.getStatusCode()==HttpStatus.UNAUTHORIZED) {
					throw new BadRequestException("Unauthorized Access");
				}else if(hce.getStatusCode()==HttpStatus.FORBIDDEN) {
					throw new BadRequestException("Forbidden Access");
				}else {
					throw new BadRequestException("Failure");
				}
			}catch(Exception e) {
				LOG.error("ERROR message is : {}",e.getMessage());
				e.printStackTrace();
				throw new BadRequestException("Generic Failure");
			}
	}

	@Override
	public boolean deleteDealerRecord(String dealerId) {
		LOG.debug("In deleteDealerRecord method of {} ",this.getClass());
		ResponseEntity<String> response = deleteRecord(dealerId);
		if(response.getStatusCode()==HttpStatus.BAD_REQUEST) {
			return false;
		}
		return true;
	}
	
	
	
	private ResponseEntity<String> deleteRecord(String dealerId) {
		try {
			String url = baseurl+getSingleRecordUrl;
		    URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(dealerId).toUri();
			LOG.debug("URI is  method of {} and {}",uri.toString(),uri.toURL().toString());
			HttpEntity<String> httpEntity = new HttpEntity<>(null, createHeaders());
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);
			return response;
			}
			catch(HttpClientErrorException hce) {
				if(hce.getStatusCode()==HttpStatus.UNAUTHORIZED) {
					throw new BadRequestException("Unauthorized Access");
				}else if(hce.getStatusCode()==HttpStatus.FORBIDDEN) {
					throw new BadRequestException("Forbidden Access");
				}else {
					throw new BadRequestException("Failure");
				}
			}catch(Exception e) {
				LOG.error("ERROR message is : {}",e.getMessage());
				throw new BadRequestException("Generic Failure");
			}
	}

	private ResponseEntity<DealerResponse> getAllRecordsRestCall() {
		try {
		String url = baseurl+getAllRecordsUrl;
		HttpEntity<String> httpEntity = new HttpEntity<>(null, createHeaders());
		ResponseEntity<DealerResponse> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, DealerResponse.class);
		return response;
		}
		catch(HttpClientErrorException hce) {
			if(hce.getStatusCode()==HttpStatus.UNAUTHORIZED) {
				throw new BadRequestException("Unauthorized Access");
			}else if(hce.getStatusCode()==HttpStatus.FORBIDDEN) {
				throw new BadRequestException("Forbidden Access");
			}else {
				throw new BadRequestException("Failure");
			}
		}catch(Exception e) {
			throw new BadRequestException("Generic Failure");
		}
	}
	
	
	private ResponseEntity<DealerResponse> getSingleRecordsRestCall(String dealerId) {
		try {
		String url = baseurl+getSingleRecordUrl;
		URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand(dealerId).toUri();
		LOG.debug("URI is  method of {} and {}",uri.toString(),uri.toURL().toString());
		HttpEntity<String> httpEntity = new HttpEntity<>(null, createHeaders());
		ResponseEntity<DealerResponse> response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, DealerResponse.class);
		return response;
		}
		catch(HttpClientErrorException hce) {
			if(hce.getStatusCode()==HttpStatus.UNAUTHORIZED) {
				throw new BadRequestException("Unauthorized Access");
			}else if(hce.getStatusCode()==HttpStatus.FORBIDDEN) {
				throw new BadRequestException("Forbidden Access");
			}else {
				throw new BadRequestException("Failure");
			}
		}catch(Exception e) {
			throw new BadRequestException("Generic Failure");
		}
	}
	
	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}

}
