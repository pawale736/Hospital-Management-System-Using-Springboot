package com.cdac.dto;

import com.cdac.entity.Patient;

public class FetchingPatientStatus {
	
	private Patient patient;
	private String messageIfAny;
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public String getMessageIfAny() {
		return messageIfAny;
	}
	public void setMessageIfAny(String messageIfAny) {
		this.messageIfAny = messageIfAny;
	}
	
	

}
