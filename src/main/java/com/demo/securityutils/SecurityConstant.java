package com.demo.securityutils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.demo.dtos.RoleDTO;


public class SecurityConstant {
	public static final String sessionSalt = "#$%roger80034ZuluTango";

	/**
	 * This Method Converts Role Set to List of SimpleGrantedAuth
	 * 
	 * @param roles
	 * @return
	 */
	public static List<SimpleGrantedAuthority> rolesToAuth(List<RoleDTO> roles) {
		List<SimpleGrantedAuthority> authArray = new ArrayList<SimpleGrantedAuthority>();
		for (RoleDTO role : roles) {
			authArray.add(new SimpleGrantedAuthority(role.getRole()));
		}
		return authArray;
	}
}