package com.demo.utilities;

import java.security.Principal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.demo.dtos.UserDTO;


public class BaseUtil {
	
	/**
	 * This util method helps fetching User Principle Object from Principal
	 * @param principal
	 * @return
	 */
	public static UserDTO getUserPrincipal(Principal principal) {
		UsernamePasswordAuthenticationToken springToken = (UsernamePasswordAuthenticationToken) principal;
		return (UserDTO) springToken.getPrincipal();
	}
}
