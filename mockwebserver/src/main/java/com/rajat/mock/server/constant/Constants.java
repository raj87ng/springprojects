package com.rajat.mock.server.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
	
	public static final String GET_ALL_RECORDS =  "/v1/records";
	public static final String GET_RECORD_NOTFOUND = "/v1/records/notfound";
	public static final String GETORDELETE_DEALER_RECORD =  "/v1/records/dealers/{dealerid}";
	public static final String POST_DEALER_RECORD =  "/v1/records/dealers";
	
	//Dealer code
	public static final String DEALER_1 = "dealer-1";
	public static final String DEALER_2 = "dealer-2";
	public static final String DEALER_3 = "dealer-3";
	public static final String DEALER_4 = "dealer-4";
	
	//Dealer Messages

			public static final String SUCCESS = "SUCCESS";
			public static final String NO_DEALER_FOUND = "No Dealer Data Found";
			public static final String NOT_SAVED = "Not Saved";
			public static final String RECORD_NOT_EXISTS = "Record Not Exists";
			public static final String RECORD_DELETED = "Record Deleted";
			public static final String SAVED = "Saved";
			public static final String UPDATED = "Updated";
}
