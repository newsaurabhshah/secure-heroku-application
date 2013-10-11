package com.demo.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.demo.exceptions.XSSSecurityException;
import com.demo.utilities.UserInputCleaner;

/**
 * Sanitizer Advisor Uses Aspect j to call Sanitizer
 * 
 * @author saurabhss
 * 
 */
@Aspect
public class UserInputCleanerAdivsor {
	/**
	 * Local Instance of Logger
	 */
	private Logger log = Logger.getLogger(UserInputCleanerAdivsor.class);
	
	/**
	 * Check Method this extract the Arg from Request and Passes to Sanitizer
	 * 
	 * @param jp
	 * @return
	 * @throws Throwable
	 */
	@Around(value = "execution(@org.springframework.web.bind.annotation.RequestMapping * * (..))")
	public Object check(final ProceedingJoinPoint jp) throws Throwable {
		log.debug("Inside Aspect");
		Object[] args = jp.getArgs();
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				nestedCheck(args[i]);
			}
		}
		return jp.proceed(args); //Needs Throwable
	}

	/**
	 * Nested Check
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws XSSSecurityException 
	 */
	private void nestedCheck(Object obj) throws XSSSecurityException {
		log.debug(obj.toString());
		if (obj != null) {
			log.debug("Object is not String Cross Verifying Complex Object");
			log.debug("Object :"+obj.toString()+" Processing...");
			if (!UserInputCleaner.isClean(obj.toString())) {
				throw new XSSSecurityException("XSS Validation has occured");				
			}
		}
	}

}