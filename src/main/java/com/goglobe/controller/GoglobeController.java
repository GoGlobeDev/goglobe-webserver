package com.goglobe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.goglobe.service.GoglobeService;

@EnableAutoConfiguration
@RestController                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
@CrossOrigin
@RequestMapping("/airdrop")
public class GoglobeController {
	@Autowired
	private GoglobeService goglobeService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestBody Map<String, String> requestMap) {
		return goglobeService.login(requestMap.get("account"), requestMap.get("invitedCode"));
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	public String sendEmail(@RequestBody Map<String,String> requestMap) {
		return goglobeService.sendEmail(requestMap.get("account"), requestMap.get("email"));
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.POST)
	public String checkCode(@RequestBody Map<String,String> map) {
		return goglobeService.activeGoglobe(map.get("account"));
	}
	
	@RequestMapping(value = "/send/code", method = RequestMethod.POST)
	public String sendCode(@RequestBody Map<String,String> requestMap) {
		return goglobeService.sendCode(requestMap.get("account"), requestMap.get("phone"));
	}
	
	@RequestMapping(value = "/active/account", method = RequestMethod.POST)
	public String activeAccount(@RequestBody Map<String,String> map) {
		return goglobeService.activeGoglobe(map.get("account"),map.get("code"));
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String selectUser(@RequestBody Map<String,String> map) {
		return goglobeService.selectByCode(map.get("code"));
	}
}
