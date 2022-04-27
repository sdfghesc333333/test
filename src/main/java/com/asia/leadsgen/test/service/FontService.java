package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.FontEntity;
import com.asia.leadsgen.test.repository.FontRepository;
import com.asia.leadsgen.test.util.SortAndDirUtils;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class FontService {

	@Autowired
	FontRepository fontRepository;

	@Autowired
	UserService userService;

	public Page<FontEntity> list(int page, int pageSize, String startDate, String endDate, String search, String sort,
			String dir, UserInfo userInfo) {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		Page<FontEntity> fontEntity = fontRepository
				.findAllByUserIdAndDeletedAt(SortAndDirUtils.sortAndDir(page, pageSize, sort, dir), userId, null);

		return fontEntity;
	}

	public FontEntity create(FontEntity fontEntity, UserInfo userInfo) throws OracleSQLException {

		try {
			fontEntity.setUserId(userService.getUser(userInfo).getId());
			fontEntity.setPath(CreateGoogleFile.uploadGoogleDrive(fontEntity.getPath()));
		} catch (LoginException e1) {
			e1.printStackTrace();
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
