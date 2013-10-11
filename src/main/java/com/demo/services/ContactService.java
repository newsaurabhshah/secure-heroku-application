package com.demo.services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.MailException;

import com.demo.constants.ApplicationConstants;
import com.demo.db.mapper.ContactMapper;
import com.demo.dtos.ContactDTO;
import com.demo.exceptions.ContactServiceException;
import com.demo.services.serviceinterface.IContactService;
import com.demo.services.serviceinterface.IEMailService;

/**
 * @author saurabhss
 * 
 *         TODO: Write Java Docs, Lots of them. #timeShortage
 */
public class ContactService implements IContactService {
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
					+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static Pattern pattern = Pattern.compile(EMAIL_PATTERN);;
	private Matcher matcher;

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	private IEMailService emailService;
	/**
	 * Local Instance of Logger
	 */
	private static Logger logger = Logger
			.getLogger(ContactService.class);

	private void _insertNewContact(ContactDTO contactDTO) {
		this.jdbcTemplateObject.update(
				ContactDTO.SQL_INSERT,
				new Object[] { contactDTO.getName(), contactDTO.getEmail(), contactDTO.getMessage(),
						contactDTO.getMobile() });
	}

	public List<ContactDTO> getAllContacts() {
		return this.jdbcTemplateObject.query(ContactDTO.SQL_FETCH, new ContactMapper());
	}

	/**
	 * @return the emailService
	 */
	public IEMailService getEmailService() {
		return emailService;
	}

	public void registerUser(ContactDTO contactDTO) throws ContactServiceException {
		StringBuilder sb = new StringBuilder();
		sb.append("Hi,").append(contactDTO.getName()).append("\n").append(contactDTO.getMessage());
		try {
			emailService.sendMail(contactDTO.getEmail(), ApplicationConstants.EMAIL_SUBJECT, sb.toString());
			_insertNewContact(contactDTO);
		} catch (MailException exception) {
			logger.error(exception);
			throw new ContactServiceException(exception.getMessage());
		}

	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	/**
	 * @param emailService the emailService to set
	 */
	public void setEmailService(IEMailService emailService) {
		this.emailService = emailService;
	}

	public boolean validateEmail(String email) {
		matcher = pattern.matcher(email);
		if (matcher.matches()) {
			List<Integer> count = this.jdbcTemplateObject.queryForList(ContactDTO.SQL_VALIDATE_EMAIL,
					new Object[] { email }, Integer.class);
			return count.isEmpty();
		}
		return false;

	}
}
