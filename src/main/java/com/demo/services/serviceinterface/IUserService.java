package com.demo.services.serviceinterface;

import com.demo.dtos.UserDTO;

/**
 * @author saurabhss
 * 
 */
public interface IUserService {
	public UserDTO getUser(String username, String password);
}
