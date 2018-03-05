package com.goglobe.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@RestController
@RequestMapping("/goglobe")
public class PingController {
	@RequestMapping(value = "/ping")
	public String ping() {
		return "running";
	}
}
