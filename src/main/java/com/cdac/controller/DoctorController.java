package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.LoginDetails;
import com.cdac.dto.LoginStatus;
import com.cdac.dto.RegistrationStatus;
import com.cdac.dto.Status;
import com.cdac.entity.Doctor;
import com.cdac.exception.DoctorServiceException;
import com.cdac.exception.PatientServiceException;
import com.cdac.service.DoctorService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;

	@PostMapping("/registerDoctor")
	public Status register(@RequestBody Doctor doctor) {
		try {
			int id = doctorService.register(doctor);
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setMessageIfAny("Registration successful!");
			status.setId(id);
			return status;
		}
		catch(DoctorServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}
	
	
	@PostMapping("/doctorLogin")
	public Status login(@RequestBody LoginDetails loginDetails) {
		try {
			Doctor doctor = doctorService.login(loginDetails.getEmail(), loginDetails.getPassword());
			LoginStatus status = new LoginStatus();
			status.setStatus(true);
			status.setMessageIfAny("Login successful!");
			status.setDoctorId(doctor.getId());
			return status;
		}
		catch (DoctorServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}
	
	@GetMapping("/fetchDoctor/{id}")
	public Doctor fetchById(@PathVariable int id) {
			return doctorService.fetchById(id);
	}
	
	@PostMapping("/fetchDoctorByName")
	public int fetchByName(@RequestBody Doctor doctor) {
			return doctorService.fetchByName(doctor);
	}
	
	
	@PostMapping("/fetchDoctors")
	public List<Doctor> fetch(){
		List<Doctor> doctors =doctorService.fetch();
		return doctors;
	}
	
	@PostMapping("/deleteDoctor/{id}")
	public Status delete(@PathVariable int id) {
		
		try {
			doctorService.delete(id);
			Status status = new Status();
			status.setStatus(true);
			status.setMessageIfAny("Doctor Deleted!");
			return status;
		} catch (DoctorServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;

		}
	}
	
	
	@PutMapping("/updateDoctor/{id}")
	public void update(@PathVariable int id,@RequestBody Doctor doctor) {
			doctorService.update(id,doctor);
			
		
	}
	

}
