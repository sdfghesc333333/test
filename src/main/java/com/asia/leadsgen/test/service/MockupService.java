package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.entity.MockupEntity;
import com.asia.leadsgen.test.repository.MockupRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class MockupService {

	@Autowired
	MockupRepository mockupRepository;

	public MockupEntity add(MockupEntity mockupEntity, long userId) throws IOException, OracleSQLException {
		mockupEntity.setUserId(userId);
		mockupEntity.setFilePath(CreateGoogleFile.uploadMockupGoogleDrive(mockupEntity.getFilePath()));
		mockupEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(mockupRepository.save(mockupEntity))) {
			return mockupRepository.save(mockupEntity);
		} else {
			throw new OracleSQLException();
		}
	}

	public MockupEntity get(Long mockupId, Long userId) {
		MockupEntity mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId, userId, null);
		if (ObjectUtils.isNotEmpty(mockupEntity)) {
			return mockupEntity;
		} else {
			throw new NotFoundException("Mockup not exist");
		}
	}

	public MockupEntity edit(Long mockupId, Long userId, MockupEntity mockupRequest)
			throws IOException, OracleSQLException {
		MockupEntity mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId, userId, null);
		if (ObjectUtils.isNotEmpty(mockupEntity)) {
			mockupEntity.setUserId(userId);
			mockupEntity.setFilePath(CreateGoogleFile.uploadMockupGoogleDrive(mockupRequest.getFilePath()));
			mockupEntity.setWidth(mockupRequest.getWidth());
			mockupEntity.setHeight(mockupRequest.getHeight());
			mockupEntity.setConditions(mockupRequest.getConditions());
			mockupEntity.setName(mockupRequest.getName());
			mockupEntity.setPrintareas(mockupRequest.getPrintareas());
			mockupEntity.setUpdatedAt(new Date());
			if (ObjectUtils.isNotEmpty(mockupRepository.save(mockupEntity))) {
				return mockupRepository.save(mockupEntity);
			} else {
				throw new OracleSQLException();
			}
		} else {
			throw new NotFoundException("Mockup not exist");
		}
	}

	public String delete(Long mockupId, Long userId) throws OracleSQLException {
		MockupEntity mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId, userId, null);
		if (ObjectUtils.isNotEmpty(mockupEntity)) {
			mockupEntity.setDeletedAt(new Date());
			if (ObjectUtils.isNotEmpty(mockupRepository.save(mockupEntity))) {
				mockupRepository.save(mockupEntity);
				return "msg : Success";
			} else {
				throw new OracleSQLException();
			}
		} else {
			throw new NotFoundException("Mockup not exist");
		}
	}
}
