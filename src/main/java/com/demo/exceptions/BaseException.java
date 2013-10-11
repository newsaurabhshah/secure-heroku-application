package com.demo.exceptions;

import org.apache.log4j.Logger;




/**
 * @author saurabhss
 *
 */
public class BaseException extends Exception{
	
	/**
	 * Local Instance of Logger
	 */
	private Logger log = Logger.getLogger(BaseException.class);
	
	/**
	 * Default Serial Number
	 */
	private static final long serialVersionUID = 1L;
	
	public BaseException(String message){
		super(message);
		log.error(message);
	}
}
