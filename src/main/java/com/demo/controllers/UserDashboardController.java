package com.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.demo.constants.ApplicationConstants;
import com.demo.dtos.ContactDTO;
import com.demo.exceptions.ContactServiceException;
import com.demo.services.serviceinterface.IContactService;

/**
 * @author saurabhss
 * 
 */
@Controller
public class UserDashboardController {
	/**
	 * Local Instance of Logger
	 */
	private static Logger logger = Logger
			.getLogger(UserDashboardController.class);

	@Autowired private IContactService contactService;

	@RequestMapping(value = "contactHistory", method = RequestMethod.GET)
	@PreAuthorize("hasRole('user')")
	public @ResponseBody
	List<ContactDTO> contactHistory(Principal user) {
		if (logger.isDebugEnabled()) {
			logger.debug("User " + user.getName() + " in UserDashboardController's contactHistory Method");
		}
		return contactService.getAllContacts();
	}

	@RequestMapping(value = "exportToPDF", method = RequestMethod.GET)
	@PreAuthorize("hasRole('user')")
	public ModelAndView exportToPDF(Principal user) {
		if (logger.isDebugEnabled()) {
			logger.debug("User " + user.getName() + " in UserDashboardController's exportToPDF Method");
		}
		return new ModelAndView("ContactPdfViewHandler", ApplicationConstants.CONTACT_MODEL,
				contactService.getAllContacts());
	}

	@RequestMapping(value = "userFormSubmit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('user')")
	public @ResponseBody
	boolean userFormSubmit(Principal user, ContactDTO contact) throws ContactServiceException {
		if (logger.isDebugEnabled()) {
			logger.debug("User " + user.getName() + " in UserDashboardController's userFormSubmit Method");
		}
		if (contactService.validateEmail(contact.getEmail())) {
			contactService.registerUser(contact);
			return true;
		}
		else {
			return false;
		}
	}

	@RequestMapping(value = "validateEmail", method = RequestMethod.GET)
	@PreAuthorize("hasRole('user')")
	public @ResponseBody
	boolean validateEmail(Principal user, @RequestParam String email) {
		if (logger.isDebugEnabled()) {
			logger.debug("User " + user.getName() + " in UserDashboardController's validateEmail Method");
		}
		return contactService.validateEmail(email);
	}

}
