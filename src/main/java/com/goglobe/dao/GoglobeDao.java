package com.goglobe.dao;

import com.goglobe.dao.model.Goglobe;

public interface GoglobeDao {
	
	Goglobe select(String account);

	Goglobe initgoglobe(String account, String invitedCode);
	
	boolean updateEmail(String account, String email);

	boolean activeGoglobe(String account);

	boolean addMoneyByInvitation(String invitedCode);

	boolean updatePhone(String account, String phone);
}
