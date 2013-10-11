package com.demo.services.serviceinterface;

import java.util.List;

import com.demo.dtos.ContactDTO;
import com.demo.exceptions.ContactServiceException;

/**
 * @author saurabhss
 * 
 */
public interface IContactService {
	public List<ContactDTO> getAllContacts();

	public void registerUser(ContactDTO contactDTO) throws ContactServiceException;

	public boolean validateEmail(String email);
}
