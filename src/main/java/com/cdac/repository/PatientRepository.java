package com.cdac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cdac.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
//	@Query("select count(p) from Patient p where p.email = ?1")
//	public Long findIfPatientExists(String email);
//	
//	@Query("select count(p) from Patient p where p.email = ?1 and p.password = ?2")
//	public Long findIfPatientIsPresent(String email, String password);
	
	public Optional<Patient> findByEmail(String email);
	public Patient findById(int id);
	public Optional<Patient> findByEmailAndPassword(String email, String password);

}
