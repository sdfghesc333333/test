package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.entity.FontEntity;
import com.asia.leadsgen.test.repository.FontRepository;

@Service
public class FontService {

	@Autowired
	FontRepository fontRepository;
	
	public FontEntity createFont(FontEntity fontEntity, long userId) throws IOException {
		fontEntity.setUserId(userId);
		fontEntity.setPath(CreateGoogleFile.uploadGoogleDrive(fontEntity.getPath()));
		fontEntity.setCreatedAt(new Date());
		return fontRepository.save(fontEntity);
	}
	
}
