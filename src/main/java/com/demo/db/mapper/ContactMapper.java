package com.demo.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.demo.dtos.ContactDTO;

/**
 * @author saurabhss
 * 
 */
public class ContactMapper implements RowMapper<ContactDTO> {

	public ContactDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		ContactDTO contact = new ContactDTO();
		contact.setEmail(rs.getString(ContactDTO.EMAIL));
		contact.setId(rs.getInt(ContactDTO.ID));
		contact.setMessage(rs.getString(ContactDTO.MESSAGE));
		contact.setMobile(rs.getString(ContactDTO.MOBILE));
		contact.setName(rs.getString(ContactDTO.NAME));
		return contact;
	}

}
