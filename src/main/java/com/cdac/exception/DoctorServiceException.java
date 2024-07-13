package com.cdac.exception;

public class DoctorServiceException extends RuntimeException {
	
	public DoctorServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoctorServiceException(String message) {
		super(message);
	}

}
