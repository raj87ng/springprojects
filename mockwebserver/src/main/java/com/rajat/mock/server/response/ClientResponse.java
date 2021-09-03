package com.rajat.mock.server.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * ClientResponse class designed with Builder Pattern
 * @author Rajat
 *
 * @param <R>
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientResponse<R> {

	private final String clientCode;
	private final String clientMessage;
	private final R clientData;             //Optional
	private final HttpStatus httpStatusCode; //Optional
	
	private ClientResponse(ResponseBuilder<R> builder) {
		this.clientCode=builder.clientCode;
		this.clientMessage=builder.clientMessage;
		this.clientData=builder.clientData;
		this.httpStatusCode=builder.httpStatusCode;
	}
	
	public static class ResponseBuilder<R>{
		private final String clientCode;
		private final String clientMessage;
		private R clientData;
		private HttpStatus httpStatusCode;
		
		public ResponseBuilder(String clientCode, String clientMessage) {
			this.clientCode=clientCode;
			this.clientMessage=clientMessage;
		}
		
		public ResponseBuilder<R> withClientData(R clientData) {
			this.clientData=clientData;
			return this;
		}
		
		public ResponseBuilder<R> withHttpCode(HttpStatus httpStatusCode) {
			this.httpStatusCode=httpStatusCode;
			return this;
		}
		
		public ClientResponse<R> build() {
			return new ClientResponse<>(this);
		}
		
	}
	
	
	
}
