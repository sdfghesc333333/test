package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.FontEntity;
import com.asia.leadsgen.test.repository.FontRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class FontService {

	@Autowired
	FontRepository fontRepository;

	@Autowired
	UserService userService;

	public FontEntity create(FontEntity fontEntity, UserInfo userInfo) throws OracleSQLException {

		try {
			fontEntity.setUserId(userService.getUser(userInfo).getId());
		} catch (LoginException e1) {
			e1.printStackTrace();
		}

		try {
			fontEntity.setPath(CreateGoogleFile.uploadGoogleDrive(fontEntity.getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		fontEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(fontRepository.save(fontEntity))) {
			return fontRepository.save(fontEntity);
		} else {
			throw new OracleSQLException();
		}

	}

}
