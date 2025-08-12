package com.cdac.dto;

import java.util.List;

import com.cdac.entity.Patient;

public class FetchingAllPatientStatus {
	
	List<Patient> patients;
	private String messageIfAny;
	
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public String getMessageIfAny() {
		return messageIfAny;
	}
	public void setMessageIfAny(String messageIfAny) {
		this.messageIfAny = messageIfAny;
	}
	
	
	
	

}
