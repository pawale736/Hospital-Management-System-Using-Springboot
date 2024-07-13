package com.cdac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Doctor;
import com.cdac.entity.Patient;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	public Optional<Doctor> findByEmail(String email);
	public Doctor findById(int id);
	public Optional<Doctor> findByEmailAndPassword(String email, String password);
	public Optional<Doctor> findByName(String name);

}
