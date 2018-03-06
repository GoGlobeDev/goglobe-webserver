package com.goglobe.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/goglobe")
public class PingController {
	
	private Logger logger = LogManager.getLogger(PingController.class);
	
	@RequestMapping(value = "/ping")
	public String ping() {
		return "goglobe running";
	}
	
	@RequestMapping(value = "/log")
	public String log() {
		logger.error("error");
		logger.info("info");
		return "goglobe log";
	}
}
