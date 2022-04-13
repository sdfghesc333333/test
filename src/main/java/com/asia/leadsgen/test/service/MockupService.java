package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.entity.MockupEntity;
import com.asia.leadsgen.test.repository.MockupRepository;

@Service
//@SuppressWarnings({ "unchecked", "rawtypes" })
public class MockupService {

	@Autowired
	MockupRepository mockupRepository;

	public MockupEntity createMockup(MockupEntity mockupEntity, long userId) throws IOException {
		mockupEntity.setUserId(userId);
		mockupEntity.setFilePath(CreateGoogleFile.uploadMockupGoogleDrive(mockupEntity.getFilePath()));
		mockupEntity.setCreatedAt(new Date());
		return mockupRepository.save(mockupEntity);
	}
}
