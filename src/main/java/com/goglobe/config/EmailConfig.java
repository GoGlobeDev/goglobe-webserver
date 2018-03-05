package com.goglobe.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {
	@Autowired
	private Environment environment;
	
	private String hostName;
	private String user;
	private String password;
	private String sendFrom;
	private String title;
	private String content;

	@PostConstruct
	private void init() {
		this.hostName = environment.getProperty("email.hostName");
		this.password = environment.getProperty("email.password");
		this.user = environment.getProperty("email.user");
		this.sendFrom = environment.getProperty("email.sendFrom");
		this.title = environment.getProperty("email.title");
		this.content = environment.getProperty("email.content");
		
	}

	public String getHostName() {
		return hostName;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getSendFrom() {
		return sendFrom;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
}
