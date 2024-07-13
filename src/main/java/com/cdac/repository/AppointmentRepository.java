package com.cdac.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cdac.entity.Appointment;
import com.cdac.entity.Doctor;
import com.cdac.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	/*
	 * public Optional<Appointment> findById(int id);
	 * 
	 * @Query("SELECT a FROM Appointment a JOIN FETCH a.patient JOIN FETCH a.doctor"
	 * ) List<Appointment> findAll();
	 */
	
	@Query("SELECT a.appointmentId, p, d FROM Appointment a JOIN a.patient p JOIN a.doctor d WHERE a.appointmentId = :appointmentId")
    Object[] findAppointmentDetails(int appointmentId);
	
	 @Query("SELECT a.appointmentId, p, d FROM Appointment a JOIN a.patient p JOIN a.doctor d")
	    List<Object[]> findAllAppointmentDetails();
	    
	    @Modifying
	    @Query("UPDATE Appointment a SET a.doctor = :doctor, a.patient = :patient, a.appointmentDate = :appointmentDate, a.appointmentTime = :appointmentTime WHERE a.appointmentId = :appointmentId")
	    void updateAppointmentDetails(int appointmentId, Doctor doctor, Patient patient, LocalDate appointmentDate,LocalTime appointmentTime);


}
