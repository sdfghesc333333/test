package com.asia.leadsgen.test.service;

import java.util.Date;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.ClipartEntity;
import com.asia.leadsgen.test.repository.ClipartRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class ClipartService {

	@Autowired
	ClipartRepository clipartRepository;

	@Autowired
	UserService userService;

	public ClipartEntity create(ClipartEntity clipartEntity, UserInfo userInfo) throws OracleSQLException {
		try {
			clipartEntity.setUserId(userService.getUser(userInfo).getId());
		} catch (LoginException e) {
			e.printStackTrace();
		}
		clipartEntity.setType("dropdown");
		clipartEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(clipartRepository.save(clipartEntity))) {
			return clipartRepository.save(clipartEntity);
		} else {
			throw new OracleSQLException();
		}
	}
}
