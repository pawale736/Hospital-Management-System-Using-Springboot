package com.cdac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdac.dto.FetchingAllPatientStatus;
import com.cdac.dto.FetchingPatientStatus;
import com.cdac.dto.LoginDetails;
import com.cdac.dto.LoginStatus;
import com.cdac.dto.RegistrationStatus;
import com.cdac.dto.Status;
import com.cdac.entity.Patient;
import com.cdac.exception.PatientServiceException;
import com.cdac.service.PatientService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/registerPatient")
	public Status register(@RequestBody Patient patient) {
		try {
			int id = patientService.register(patient);
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setMessageIfAny("Registration successful!");
			status.setId(id);
			return status;
		} catch (PatientServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}

	@PutMapping("/bookPatient/{id}")
	public Status update(@PathVariable int id, @RequestBody Patient patient) {
		try {
			Patient patient1 = patientService.update(id, patient);
			RegistrationStatus status = new RegistrationStatus();
			status.setStatus(true);
			status.setMessageIfAny("Patient Updated Successfully");
			status.setId(id);
			return status;
		} catch (PatientServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}

	@PostMapping("/patientLogin")
	public Status login(@RequestBody LoginDetails loginDetails) {
		try {
			Patient patient = patientService.login(loginDetails.getEmail(), loginDetails.getPassword());
			LoginStatus status = new LoginStatus();
			status.setStatus(true);
			status.setMessageIfAny("Login successful!");
			status.setPatientId(patient.getId());
			return status;
		} catch (PatientServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}

	@GetMapping("/fetchPatient/{id}")
	public FetchingPatientStatus fetchById(@PathVariable int id) {
		try {
			Patient patient = patientService.fetchById(id);
			FetchingPatientStatus p = new FetchingPatientStatus();
			p.setPatient(patient);
			return p;
		} catch (PatientServiceException e) {
			FetchingPatientStatus p1 = new FetchingPatientStatus();
			p1.setMessageIfAny(e.getMessage());
			return p1;
		}
	}

	@GetMapping("/fetchPatients")
	public FetchingAllPatientStatus fetch() {
		try {
			List<Patient> patients = patientService.fetch();
			FetchingAllPatientStatus p = new FetchingAllPatientStatus();
			p.setPatients(patients);
			return p;
		} catch (PatientServiceException e) {
			FetchingAllPatientStatus p1 = new FetchingAllPatientStatus();
			p1.setMessageIfAny(e.getMessage());
			return p1;
		}
	}

	@DeleteMapping("/deletePatient/{id}")
	public Status delete(@PathVariable int id) {
		try {
			patientService.delete(id);
			Status status = new Status();
			status.setStatus(true);
			status.setMessageIfAny("Patient Deleted!");
			return status;
		} catch (PatientServiceException e) {
			Status status = new Status();
			status.setStatus(false);
			status.setMessageIfAny(e.getMessage());
			return status;
		}
	}

}
