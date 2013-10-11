package com.demo.services;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.db.mapper.UserMapper;
import com.demo.dtos.UserDTO;
import com.demo.services.serviceinterface.IUserService;
import com.demo.utilities.CrpticUtility;

/**
 * @author saurabhss
 * 
 */
public class UserService implements IUserService {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	private UserDTO _getUserDTO(String username) {
		List<UserDTO> userDTOs = this.jdbcTemplateObject.query(UserDTO.SQL_FETCH, new Object[] { username },
				new UserMapper());
		if (null == userDTOs || userDTOs.isEmpty()) {
			return null;
		}
		return userDTOs.get(0);
	}

	/**
	 * This method is a service to Authenticate User
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public UserDTO getUser(String username, String password) {
		UserDTO userDTO = _getUserDTO(username);
		if (null != userDTO && userDTO.getEncPassword().equals(CrpticUtility.encrypt(password))) {
			return userDTO;
		}
		else {
			return null;
		}
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
}
