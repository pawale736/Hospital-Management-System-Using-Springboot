package com.cdac.dto;

import com.cdac.entity.Appointment;

public class FetchingAppointmentStatus {
	
	private Object[] appointment;
	private String messageIfAny;
	
	public Object[] getAppointment() {
		return appointment;
	}
	public void setAppointment(Object[] appointment2) {
		this.appointment = appointment2;
	}
	public String getMessageIfAny() {
		return messageIfAny;
	}
	public void setMessageIfAny(String messageIfAny) {
		this.messageIfAny = messageIfAny;
	}

}
