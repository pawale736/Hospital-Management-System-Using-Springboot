package com.cdac.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Appointment;
import com.cdac.entity.Doctor;
import com.cdac.entity.Patient;
import com.cdac.exception.AppointmentServiceException;
import com.cdac.exception.PatientServiceException;
import com.cdac.repository.AppointmentRepository;
import com.cdac.repository.DoctorRepository;
import com.cdac.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	public int create(Appointment appointment) {

		
		int pid=appointment.getPatient().getId();
		int did=appointment.getDoctor().getId();
		Patient patient1 = patientRepository.findById(pid);
		Doctor doctor1 = doctorRepository.findById(did);

		if (patient1 != null && doctor1 != null) {

			Appointment apt = new Appointment();
			apt.setDoctor(doctor1);
			apt.setPatient(patient1);
			apt.setAppointmentDate(appointment.getAppointmentDate());
			apt.setAppointmentTime(appointment.getAppointmentTime());
			appointmentRepository.save(apt);
			return apt.getAppointmentId();
		} else {
			throw new AppointmentServiceException("Patient not available or Doctor not available");
		}
	}
	
	public Object[] getAppointmentDetails(int appointmentId)throws AppointmentServiceException {
        Object []appointment= appointmentRepository.findAppointmentDetails(appointmentId);
        
        if(appointment.length!=0)
        {
        	return appointment;
        }
        else
        {
        	throw new AppointmentServiceException("Enter appropriate appointment id");
        }
        
    }
	
	public List<Object[]> getAllAppointmentDetails() {
        return appointmentRepository.findAllAppointmentDetails();
    }
	
	 public void updateAppointment(int appointmentId, Doctor doctor, Patient patient, LocalDate appointmentDate, LocalTime appointmentTime) {
	        
		 appointmentRepository.updateAppointmentDetails(appointmentId, doctor, patient, appointmentDate, appointmentTime);
	    }



	public boolean delete(int id) throws AppointmentServiceException {
		Optional<Appointment> appointment1= appointmentRepository.findById(id);
		if(appointment1.isPresent()) {
			appointmentRepository.delete(appointment1.get());
		     return true;}
		else {
			throw new AppointmentServiceException("Appointment not available");
		}
		
	}

}
