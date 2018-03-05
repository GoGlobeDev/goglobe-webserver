package com.goglobe.component;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.goglobe.config.EmailConfig;

@Component
public class EmailComponent {
	@Autowired
	private EmailConfig emailConfig;

	public void sendEmail(String title, String content, String fromName, String address) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName(emailConfig.getHostName());
		email.setAuthenticator(new DefaultAuthenticator(emailConfig.getUser(), emailConfig.getPassword()));
		email.setSubject(title);
		email.setSSLOnConnect(true);
		email.setStartTLSEnabled(true);
		email.setCharset("UTF-8");
		email.setFrom(emailConfig.getUser(), fromName);
		email.addTo(address);
		email.setMsg(content);
		email.send();
	}
}
