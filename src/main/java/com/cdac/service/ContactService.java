package com.cdac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.entity.Contact;
import com.cdac.exception.ContactServiceException;
import com.cdac.repository.ContactRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;

	public void saveContact(Contact contact) {
		 contactRepository.save(contact);
		
	}

	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	public Contact getContactById(int id) {
		Optional<Contact> optionalContact = contactRepository.findById(id);
	        if (optionalContact.isPresent()) {
	            return optionalContact.get();
	        } else {
	            throw new ContactServiceException("Contact not found with id: " + id);
	        }
	}

	public void deleteContactById(int id) {
		 if (!contactRepository.existsById(id)) {

	            throw new RuntimeException("Contact with ID " + id + " not found");
	        }

	        contactRepository.deleteById(id);
		
	}

}
