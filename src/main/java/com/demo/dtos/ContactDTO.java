package com.demo.dtos;

/**
 * Data Transfer Object for Contact Table
 * 
 * @author saurabhss
 * 
 */
public class ContactDTO {

	public static final String TABLE_NAME = "contacts";
	public static final String NAME = "name";
	public static final String EMAIL = "email";
	public static final String MESSAGE = "message";
	public static final String MOBILE = "mobile";
	public static final String ID = "id";

	public static final String SQL_FETCH;
	public static final String SQL_INSERT;
	public static final String SQL_VALIDATE_EMAIL;

	static {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ").append(ID).append(", ").append(NAME).append(", ").append(EMAIL).append(", ")
				.append(MESSAGE).append(", ").append(MOBILE).append(" FROM ").append(TABLE_NAME).append(" ORDER BY ")
				.append(ID);
		SQL_FETCH = sb.toString();

		sb = new StringBuilder();
		sb.append("INSERT INTO ").append(TABLE_NAME).append(" (").append(NAME).append(", ").append(EMAIL).append(", ")
				.append(MESSAGE).append(", ").append(MOBILE).append(") VALUES(?,?,?,?)");
		SQL_INSERT = sb.toString();

		sb = new StringBuilder();
		sb.append("SELECT 1 FROM ").append(TABLE_NAME).append(" WHERE ").append(EMAIL).append("=?");
		SQL_VALIDATE_EMAIL = sb.toString();
	}
	private int id;
	private String name;
	private String mobile;
	private String email;
	private String message;

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
		ContactDTO other = (ContactDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ContactDTO [id=" + id + ", name=" + name + ", mobile=" + mobile + ", email=" + email + ", message="
				+ message + "]";
	}

}
