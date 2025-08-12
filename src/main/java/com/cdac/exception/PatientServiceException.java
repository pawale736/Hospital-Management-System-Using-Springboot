package com.cdac.exception;

public class PatientServiceException extends RuntimeException {

	public PatientServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public PatientServiceException(String message) {
		super(message);
	}
}
