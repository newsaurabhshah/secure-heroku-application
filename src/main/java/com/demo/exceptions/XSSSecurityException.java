package com.demo.exceptions;

import com.demo.exceptions.BaseException;

/**
 * @author saurabhss
 *
 */
public class XSSSecurityException extends BaseException{

	/**
	 * Default Serial Number
	 */
	private static final long serialVersionUID = 1L;

	public XSSSecurityException(String message) {
		super(message);
	}

}
