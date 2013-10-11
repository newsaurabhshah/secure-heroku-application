package com.demo.utilities;

import java.net.URL;

import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;

/**Sanitizer Class
 * @author saurabhss
 *
 */
public class UserInputCleaner {

	/**
	 * Local static instance of Policy
	 */
	private static Policy policy;
	/**
	 * Local static Instance of Antisamy
	 */
	private static AntiSamy antiSamy;

	

	/**
	 * Static Sanitize method for cleaning inputs
	 * 
	 * @param input
	 * @return
	 */
	public static String sanitize(String input) {
		CleanResults cr;
		try {
			cr = getAntiSamy().scan(input, policy);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return cr.getCleanHTML();
	}
	
	/**
	 * Static Sanitize method for cleaning inputs
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isClean(String input) {
		CleanResults cr;
		try {
			cr = getAntiSamy().scan(input, policy);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return (cr.getNumberOfErrors()==0);
	}

	/**
	 * Getter Method For Policy
	 * 
	 * @param name
	 * @return
	 * @throws PolicyException
	 */
	private static Policy getPolicy(String name) throws PolicyException {
		URL policyUrl=UserInputCleaner.class.getClassLoader().getResource("policy.xml");
		Policy policy = Policy.getInstance(policyUrl);
		return policy;
	}
	
	/**
	 * Getter Method for AntiSamy
	 * 
	 * @return
	 * @throws PolicyException
	 */
	private static AntiSamy getAntiSamy() throws PolicyException {
		if (antiSamy == null) {
			policy = getPolicy("evocatus-default");
			antiSamy = new AntiSamy();
		}
		return antiSamy;

	}

}