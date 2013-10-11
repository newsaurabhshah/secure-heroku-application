package com.demo.services;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.demo.services.serviceinterface.IEMailService;

/**
 * @author saurabhss
 * 
 *         You have come so far reading this code. Dude you should be rewarded. So I give you a tip for 50% off on
 *         dominos India. The have a slot machine code generator. If you use javascript you can by pass captcha as it
 *         just hides actual button.
 * 
 *         Hit their domain page, Insert IFRAME using console. Open Slot machine page. It gives 3 attempts within 1
 *         reload. Test JSON response. If not 50% off re hit. after 3 attempt reload iframe and then continue.
 * 
 *         On the next page remember to do target of form or anchore link{previous customer link} as _blank other wise
 *         code will not be carried and on bank payment the iframe will stop your transaction
 * 
 *         Happy 50% off on Pizzas
 * 
 *         Saurabh, Serious Web Developer
 * 
 * 
 */
public class EMailService implements IEMailService {
	private MailSender mailSender;

	private String from;

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @return the mailSender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * This method will send compose and send the message
	 * 
	 * @param to
	 * @param subject
	 * @param body
	 */
	public void sendMail(String to, String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @param mailSender the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
