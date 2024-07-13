package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Patient;
import com.cdac.exception.PatientServiceException;
import com.cdac.repository.PatientRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public int register(Patient patient) {
		Optional<Patient> patCheck = patientRepository.findByEmail(patient.getEmail());
		if (patCheck.isPresent())
			throw new PatientServiceException("Patient already registered!");
		else {
			patientRepository.save(patient);
			return patient.getId();
		}
	}

	public Patient update(int id, Patient patient) {
		Patient patient1 = patientRepository.findById(id);
		if (patient1 != null) {
			patient1.setFullName(patient.getFullName());
			patient1.setEmail(patient.getEmail());
			patient1.setContactNumber(patient.getContactNumber());
			patient1.setDisease(patient.getDisease());
			patient1.setBloodGroup(patient.getBloodGroup());
			patient1.setDepartment(patient.getDepartment());
			patient1.setAge(patient.getAge());
			patient1.setGender(patient.getGender());
			patient1.setAddress(patient.getAddress());
			patient1.setDate(patient.getDate());
			patient1.setTime(patient.getTime());
		} else {
			throw new PatientServiceException("Patient not registered, please register");
		}
		Patient patient2 = patientRepository.save(patient1);
		return patient2;
	}

	public Patient login(String email, String password) {
		Optional<Patient> patient = patientRepository.findByEmailAndPassword(email, password);
		if (patient.isPresent())
			return patient.get();
		else
			throw new PatientServiceException("Invalid Email/Password");
	}

	public Patient fetchById(int id) {
		Patient patient = patientRepository.findById(id);
		if (patient != null)
			return patient;
		else
			throw new PatientServiceException("Patient with id " + id + " does not exist!");
	}

	public List<Patient> fetch() {
		List<Patient> patients = patientRepository.findAll();
		if (patients != null)
			return patients;
		else
			throw new PatientServiceException("Patient are not available");

	}

	public boolean delete(int id) {
		Patient patient = patientRepository.findById(id);
		if (patient != null) {
			patientRepository.delete(patient);
			return true;
		} else {
			throw new PatientServiceException("Invalid id");
		}
	}
}
