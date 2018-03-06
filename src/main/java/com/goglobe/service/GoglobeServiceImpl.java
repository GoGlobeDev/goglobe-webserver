package com.goglobe.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goglobe.component.EmailComponent;
import com.goglobe.config.EmailConfig;
import com.goglobe.config.GoglobeConfig;
import com.goglobe.dao.GoglobeDao;
import com.goglobe.dao.model.Goglobe;
import com.goglobe.util.JacksonUtil;

@Service
public class GoglobeServiceImpl implements GoglobeService {
	@Autowired
	private GoglobeDao goglobeDao;
	@Autowired
	private EmailComponent emailComponent;
	@Autowired
	private GoglobeConfig goglobeConfig;
	@Autowired
	private EmailConfig emailConfig;

	private Logger logger = LogManager.getLogger(GoglobeServiceImpl.class);

	@Override
	public String login(String account, String invitedCode) {
		Map<String, Object> resMap = new HashMap<>();
		resMap.put("status", "success");
		Goglobe goglobe = goglobeDao.select(account);
		if (null == goglobe) {
			if (goglobeConfig.isEnd()) {
				resMap.put("status", "success_end");
				return JacksonUtil.objToJsonWithoutNull(resMap);
			}
			goglobe = goglobeDao.initgoglobe(account, invitedCode);

		}
		resMap.put("goglobe", goglobe);
		return JacksonUtil.objToJsonWithoutNull(resMap);
	}

	@Override
	public String sendEmail(String account, String email) {
		Map<String, String> resMap = new HashMap<>();
		resMap.put("status", "fail");
		if (StringUtils.isAnyBlank(account, email) || !email.contains("@")) {
			return JacksonUtil.objToJsonWithoutNull(resMap);
		}
		try {
			String content = emailConfig.getContent() + account;
			emailComponent.sendEmail(emailConfig.getTitle(), "<a href = \"" + content + "\">" + content + "</a>",
					emailConfig.getSendFrom(), email);
		} catch (Exception e) {
			e.printStackTrace();
			return JacksonUtil.objToJsonWithoutNull(resMap);
		}
		if (!goglobeDao.updateEmail(account, email)) {
			logger.error("updateEmail wrong, account:" + account + ",email:" + email);
		}
		resMap.put("status", "success");
		return JacksonUtil.objToJsonWithoutNull(resMap);
	}

	@Override
	public String activeGoglobe(String account) {
		Map<String, Object> resMap = new HashMap<>();
		Goglobe goglobe = goglobeDao.select(account);
		if (null == goglobe) {
			resMap.put("status", "fail_nouser");
			return JacksonUtil.objToJsonWithoutNull(resMap);
		}

		if (goglobeConfig.getActiveStatus().equals(goglobe.getStatus())) {
			resMap.put("status", "success_already");
			resMap.put("goglobe", goglobe);
			return JacksonUtil.objToJsonWithoutNull(resMap);
		}

		if (!goglobeDao.activeGoglobe(account)) {
			resMap.put("status", "fail_active");
			return JacksonUtil.objToJsonWithoutNull(resMap);
		}
		resMap.put("status", "success");
		resMap.put("goglobe", goglobeDao.select(account));
		if (StringUtils.isNotBlank(goglobe.getInvitedCode())) {
			if (!goglobeDao.addMoneyByInvitation(goglobe.getInvitedCode())) {
				logger.error("add money by invitation wrong, invitedCode:" + goglobe.getInvitedCode());
			}
		}
		return JacksonUtil.objToJsonWithoutNull(resMap);
	}

}
