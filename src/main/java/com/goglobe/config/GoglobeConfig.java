package com.goglobe.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class GoglobeConfig {
	@Autowired
	private Environment environment;
	
	private Integer initMoney;
	private Integer addMoney;
	private Integer activeStatus;
	private Integer unActiveStatus;
	private Boolean isEnd;
	
	@PostConstruct
	private void init() {
		this.initMoney = Integer.valueOf(environment.getProperty("goglobe.initMoney"));
		this.addMoney = Integer.valueOf(environment.getProperty("goglobe.addMoney"));
		this.activeStatus = Integer.valueOf(environment.getProperty("goglobe.activeStatus"));
		this.unActiveStatus = Integer.valueOf(environment.getProperty("goglobe.unActiveStatus"));
		this.isEnd = Boolean.valueOf(environment.getProperty("goglobe.isEnd"));
	}
	
	public Integer getInitMoney() {
		return initMoney;
	}
	public Integer getAddMoney() {
		return addMoney;
	}
	public Integer getActiveStatus() {
		return activeStatus;
	}
	public Integer getUnActiveStatus() {
		return unActiveStatus;
	}
	public Boolean isEnd() {
		return isEnd;
	}
}
