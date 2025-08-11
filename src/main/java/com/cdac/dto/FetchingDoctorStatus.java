package com.cdac.dto;

import com.cdac.entity.Doctor;

public class FetchingDoctorStatus {
	
	private Doctor doctor;
	private String messageIfAny;
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getMessageIfAny() {
		return messageIfAny;
	}
	public void setMessageIfAny(String messageIfAny) {
		this.messageIfAny = messageIfAny;
	}
	

}
