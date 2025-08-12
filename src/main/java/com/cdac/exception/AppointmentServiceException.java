package com.cdac.exception;

public class AppointmentServiceException extends RuntimeException{
	
	public AppointmentServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppointmentServiceException(String message) {
		super(message);
	}

}
