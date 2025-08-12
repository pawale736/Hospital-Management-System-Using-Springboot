package com.cdac.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdac.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
	
	public Optional<Contact> findById(int id);

}
