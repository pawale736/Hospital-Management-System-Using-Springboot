package com.cdac.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.cdac.entity.Doctor;
import com.cdac.entity.Patient;

public class AppointmentUpdateRequest {
	
	private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private Doctor doctor;
    private Patient patient;
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
    
    
    

}
