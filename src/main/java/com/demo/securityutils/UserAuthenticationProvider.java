package com.demo.securityutils;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.demo.dtos.UserDTO;
import com.demo.services.serviceinterface.IUserService;

/**
 * Spring Security customization of Authentication Provider
 * 
 * @author shah
 * 
 */
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired private IUserService userService;

	/**
	 * Local Instance of Logger
	 */
	private static Logger logger = Logger
			.getLogger(UserAuthenticationProvider.class);

	/*
	 * Authenticate method.
	 * 
	 * This method ensures the user is authorized. Invokes Service layer and get response back.
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#
	 * authenticate(org.springframework.security.core.Authentication)
	 */
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		// Extract Token Object
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
		// Extract Username and password
		String username = String.valueOf(auth.getPrincipal());
		String password = String.valueOf(auth.getCredentials());
		if (null == username || username.equals("") || null == password || password.equals("")) {
			throw new BadCredentialsException("Empty Credentials");
		}
		logger.info("username:" + username);
		// init User
		UserDTO user = userService.getUser(username, password);
		if (null == user) {
			throw new BadCredentialsException("Bad Credentials");
		}
		Collection<SimpleGrantedAuthority> authorities = SecurityConstant
				.rolesToAuth(user.getRoles());
		auth = new UsernamePasswordAuthenticationToken(user, password,
				authorities);
		return auth;
	}

	/**
	 * @return the userService
	 */
	public IUserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public boolean supports(Class<?> arg0) {
		// indicates that this authentication provider can handle the
		// authentication request.
		return true;
	}

}
