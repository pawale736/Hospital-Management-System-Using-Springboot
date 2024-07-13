package com.cdac.exception;

public class ContactServiceException extends RuntimeException{
	
	public ContactServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContactServiceException(String message) {
		super(message);
	}

}
