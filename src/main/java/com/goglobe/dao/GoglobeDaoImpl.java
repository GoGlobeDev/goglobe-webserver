package com.goglobe.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goglobe.config.GoglobeConfig;
import com.goglobe.dao.mapper.GoglobeMapper;
import com.goglobe.dao.model.Goglobe;
import com.goglobe.util.RandomUtil;

@Repository
public class GoglobeDaoImpl implements GoglobeDao {
	@Autowired
	private GoglobeMapper goglobeMapper;
	@Autowired
	private GoglobeConfig goglobeConfig;

	@Override
	public Goglobe select(String account) {
		return goglobeMapper.selectByAccount(account);
	}

	@Override
	public Goglobe initgoglobe(String account, String invitedCode) {
		Goglobe goglobe = new Goglobe(account, getCode(), invitedCode, goglobeConfig.getUnActiveStatus());
		goglobeMapper.insertSelective(goglobe);
		return goglobe;
	}
	
	@Override
	public boolean updateEmail(String account, String email) {
		return 1 == goglobeMapper.updateGoglobeByAccount(new Goglobe(account, email));
	}
	
	@Override
	public boolean updatePhone(String account, String phone) {
		Goglobe gog = new Goglobe();
		gog.setAccount(account);
		gog.setPhone(phone);
		return 1 == goglobeMapper.updateGoglobeByAccount(gog);
	}

	@Override
	public boolean activeGoglobe(String account) {
		return 1 == goglobeMapper.updateGoglobeByAccount(new Goglobe(account, goglobeConfig.getInitMoney(), goglobeConfig.getActiveStatus()));
	}

	@Override
	public boolean addMoneyByInvitation(String invitedCode) {
		return 1 == goglobeMapper.addMoneyByInvitation(new Goglobe(invitedCode, goglobeConfig.getAddMoney()));
	}
	
	private String getCode() {
		String code = RandomUtil.randomCharBoth(8);
		while (true) {
			Integer resId = goglobeMapper.selectByCode(code);
			if (null == resId || 0 == resId) {
				break;
			}
			code = RandomUtil.randomCharBoth(8);
		}
		return code;
	}

	@Override
	public Goglobe selectByCode(String code) {
		return goglobeMapper.selectGoglobeByCode(code);
	}

	@Override
	public Integer selectNumberByInvitedCode(String code) {
		return null;
	}
}
