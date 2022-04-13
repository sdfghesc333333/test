package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.entity.FontEntity;
import com.asia.leadsgen.test.repository.FontRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class FontService {

	@Autowired
	FontRepository fontRepository;

	public FontEntity create(FontEntity fontEntity, long userId) throws IOException, OracleSQLException {
		fontEntity.setUserId(userId);
		fontEntity.setPath(CreateGoogleFile.uploadGoogleDrive(fontEntity.getPath()));
		fontEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(fontRepository.save(fontEntity))) {
			return fontRepository.save(fontEntity);
		} else {
			throw new OracleSQLException();
		}

	}

}
