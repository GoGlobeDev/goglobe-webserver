package com.goglobe.service;

public interface GoglobeService {

	String login(String account, String invitedCode);

	String activeGoglobe(String account);

	String sendEmail(String account, String email);

}
