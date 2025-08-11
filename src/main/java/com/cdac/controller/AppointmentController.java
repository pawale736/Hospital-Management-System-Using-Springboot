package com.cdac.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.AppointmentUpdateRequest;
import com.cdac.dto.FetchingAppointmentStatus;
import com.cdac.dto.FetchingPatientStatus;
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
		} catch (AppointmentServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}

	@GetMapping("/fetchAppointment/{id}")
	public FetchingAppointmentStatus fetchById(@PathVariable int id) {
		try {
			Object appointment[] = appointmentService.getAppointmentDetails(id);
			FetchingAppointmentStatus a = new FetchingAppointmentStatus();
			a.setAppointment(appointment);
			return a;
		} catch (AppointmentServiceException e) {
			FetchingAppointmentStatus a = new FetchingAppointmentStatus();
			a.setMessageIfAny(e.getMessage());
			return a;
		}
	}

	@GetMapping("/fetchAllAppointments")
	public List<Object[]> fetch() {
		List<Object[]> appointments = appointmentService.getAllAppointmentDetails();
		return appointments;
	}

	@DeleteMapping("/deleteAppointment/{id}")
	public Status delete(@PathVariable int id) {

		try {
			appointmentService.delete(id);
			Status status = new Status();
			status.setStatus(true);
			status.setMessageIfAny("Appointment Deleted!");
			return status;
		} catch (AppointmentServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;

		}
	}

	@PutMapping("/update/{appointmentId}")
	public Status updateAppointment(@PathVariable int appointmentId,
			@RequestBody AppointmentUpdateRequest updateRequest) {
		try {
			appointmentService.updateAppointment(appointmentId, updateRequest.getDoctor(), updateRequest.getPatient(),
					updateRequest.getAppointmentDate(), updateRequest.getAppointmentTime());
			Status status = new Status();
			status.setStatus(true);
			status.setMessageIfAny("Appointment Successfully Updated");
			return status;
		
			
		} catch (AppointmentServiceException  e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
		}

}
