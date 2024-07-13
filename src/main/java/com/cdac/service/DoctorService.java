package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Doctor;
import com.cdac.exception.DoctorServiceException;
import com.cdac.exception.PatientServiceException;
import com.cdac.repository.DoctorRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DoctorService {
	

	@Autowired
	private DoctorRepository doctorRepository;
	
	public int register(Doctor doctor) {
		Optional<Doctor> docCheck= doctorRepository.findByEmail(doctor.getEmail());
		if(docCheck.isPresent())
			throw new DoctorServiceException("Doctor already registered!");
		else {
			
			doctorRepository.save(doctor);
			return doctor.getId();
			 } 
	}

	public Doctor login(String email, String password) {
		Optional<Doctor> doctor = doctorRepository.findByEmailAndPassword(email, password);
		if(doctor.isPresent())
			return doctor.get();
		else
			throw new DoctorServiceException("Invalid Email/Password");
	}

	public Doctor fetchById(int id) {
		Doctor doctor =doctorRepository.findById(id);
		   if(doctor!=null)
			  return doctor;
		  else throw new DoctorServiceException("Doctor with id " + id +" does not exist!"); 
		   
	}

	public List<Doctor> fetch() {
		 List<Doctor>doctors=doctorRepository.findAll();
		  return doctors;
	}

	public boolean delete(int id) {
		Doctor doctor1= doctorRepository.findById(id);
		if(doctor1!=null) {
			 doctorRepository.delete(doctor1);
		     return true;}
		else {
			throw new DoctorServiceException("Invalid id");
		}
		
	}

	public void update(int id, Doctor doctor) {
		//Optional<Doctor> doctor1= doctorRepository.findById(doctor.getId());
		Doctor doctor1=doctorRepository.findById(id);
		if(doctor1!=null)
		{
			doctor1.setName(doctor.getName());
			doctor1.setEmail(doctor.getEmail());
			doctor1.setContactNumber(doctor.getContactNumber());
			doctor1.setBloodGroup(doctor.getBloodGroup());
			doctor1.setDepartment(doctor.getDepartment());
			doctor1.setAge(doctor.getAge());
			doctor1.setGender(doctor.getGender());
			doctor1.setAddress(doctor.getAddress());
			doctor1.setBirthDate(doctor.getBirthDate());
			doctor1.setEducation(doctor.getEducation());
			doctor1.setOtherDetails(doctor.getOtherDetails());
			doctor1.setPassword(doctor.getPassword());
		}
		else
		{
			throw new PatientServiceException("Doctor not registered, please register");
		}
		
	}

	public int fetchByName(Doctor doctor) {
		// TODO Auto-generated method stub
		String name1=doctor.getName();
		Optional<Doctor> doctor2=doctorRepository.findByName(name1);
		return doctor2.get().getId();
	}
	
	


}
