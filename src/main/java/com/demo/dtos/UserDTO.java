package com.demo.dtos;

import java.util.List;

/**
 * This is the User Data Transfer Object
 * 
 * @author saurabhss
 * 
 */
public class UserDTO {

	public static final String TABLE_NAME = "users";
	public static final String USER_NAME = "username";
	public static final String ENC_PASS = "encpass";
	public static final String ROLE = "role";

	public static final String SQL_FETCH;

	static {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ").append(USER_NAME).append(", ").append(ENC_PASS).append(", ").append(ROLE).append(" FROM ")
				.append(TABLE_NAME).append(" WHERE ").append(USER_NAME).append(" =?");
		SQL_FETCH = sb.toString();
	}

	/**
	 * Holds Username
	 */
	private String username;
	/**
	 * Holds password
	 */
	private String encPassword;

	/**
	 * Holds The User Roles
	 */
	private List<RoleDTO> roles;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		if (encPassword == null) {
			if (other.encPassword != null)
				return false;
		} else if (!encPassword.equals(other.encPassword))
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	/**
	 * @return the password
	 */
	public String getEncPassword() {
		return encPassword;
	}

	/**
	 * @return the roles
	 */
	public List<RoleDTO> getRoles() {
		return roles;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((encPassword == null) ? 0 : encPassword.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	/**
	 * @param password the password to set
	 */
	public void setEncPassword(String encPassword) {
		this.encPassword = encPassword;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserDTO [username=" + username + ", encPassword=" + encPassword + ", roles=" + roles + "]";
	}

}
