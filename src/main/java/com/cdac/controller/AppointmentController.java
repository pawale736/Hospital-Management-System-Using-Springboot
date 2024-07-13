package com.cdac.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.RegistrationStatus;
import com.cdac.dto.Status;
import com.cdac.entity.Appointment;
import com.cdac.entity.Doctor;
import com.cdac.entity.Patient;
import com.cdac.exception.AppointmentServiceException;
import com.cdac.exception.PatientServiceException;
import com.cdac.service.AppointmentService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/bookAppointment")
	public Status create(@RequestBody Appointment appointment) {
		try {
			int id = appointmentService.create(appointment);
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setMessageIfAny("Appointment successful!");
			status.setId(id);
			return status;
		}
		catch(AppointmentServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/fetchAppointment/{id}")
	public Object[] fetchById(@PathVariable int id) {
			return appointmentService.getAppointmentDetails(id);
	}
	
	@GetMapping("/fetchAllAppointments")
	public List<Object[]> fetch(){
		List<Object[]> appointments =appointmentService.getAllAppointmentDetails();
		return appointments;
	}
	
	
	
	@PostMapping("/deleteAppointment/{id}")
	public Status delete(@PathVariable int id) {
		
		try {
			appointmentService.delete(id);
			Status status = new Status();
			status.setStatus(true);
			status.setMessageIfAny("Appointment Deleted!");
			return status;
		} catch (PatientServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;

		}
	}
	
//	@PutMapping("/appointments/update")
//    public void updateAppointment(@RequestParam int appointmentId, @RequestBody Doctor doctor,
//                                  @RequestBody Patient patient, @RequestParam LocalDate appointmentDate) {
//        appointmentService.updateAppointment(appointmentId, doctor, patient, appointmentDate);
//    }
	
	 @PutMapping("/appointments/update/{appointmentId}")
	    public void updateAppointment(@PathVariable int appointmentId, 
	    		@RequestBody LocalDate appointmentDate,
	                                                    @RequestBody LocalTime appointmentTime,
	                                                    @RequestBody Doctor doctor,
	                                                    @RequestBody Patient patient) {
	        appointmentService.updateAppointment(appointmentId, doctor, patient, appointmentDate, appointmentTime);
	        
	    }
	
	

}
