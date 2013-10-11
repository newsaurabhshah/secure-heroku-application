package com.demo.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.demo.dtos.RoleDTO;
import com.demo.dtos.UserDTO;

/**
 * @author saurabhss
 * 
 */
public class UserMapper implements RowMapper<UserDTO> {

	public UserDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		UserDTO user = new UserDTO();
		user.setUsername(rs.getString(UserDTO.USER_NAME));
		user.setEncPassword(rs.getString(UserDTO.ENC_PASS));
		RoleDTO role = new RoleDTO();
		role.setRoleId(1);
		role.setRole(rs.getString(UserDTO.ROLE));
		user.setRoles(Arrays.asList(role));
		return user;
	}

}
