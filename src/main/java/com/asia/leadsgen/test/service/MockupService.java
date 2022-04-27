package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.MockupEntity;
import com.asia.leadsgen.test.repository.MockupRepository;
import com.asia.leadsgen.test.util.SortAndDirUtils;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class MockupService {

	@Autowired
	MockupRepository mockupRepository;

	@Autowired
	UserService userService;

	public Page<MockupEntity> list(int page, int pageSize, String startDate, String endDate, String search, String sort,
			String dir, UserInfo userInfo) {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		Page<MockupEntity> mockupEntity = mockupRepository
				.findAllByUserIdAndDeletedAt(SortAndDirUtils.sortAndDir(page, pageSize, sort, dir), userId, null);

		return mockupEntity;
	}

	public Page<MockupEntity> getListForMockupPage(int page, int pageSize, String startDate, String endDate,
			String search, String sort, String dir, UserInfo userInfo) {

		Page<MockupEntity> mockupEntity;

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (StringUtils.isEmpty(search)) {
			mockupEntity = mockupRepository
					.findAllByUserIdAndDeletedAt(SortAndDirUtils.sortAndDir(page, pageSize, sort, dir), userId, null);
		} else {
			mockupEntity = mockupRepository.findAllByUserIdAndDeletedAtAndNameLike(
					SortAndDirUtils.sortAndDir(page, pageSize, sort, dir), userId, null, search);
		}

		return mockupEntity;
	}

	public MockupEntity add(MockupEntity mockupEntity, UserInfo userInfo) throws OracleSQLException {
		try {
			mockupEntity.setUserId(userService.getUser(userInfo).getId());
			mockupEntity.setFilePath(CreateGoogleFile.uploadMockupGoogleDrive(mockupEntity.getFilePath()));
		} catch (LoginException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mockupEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(mockupRepository.save(mockupEntity))) {
			return mockupRepository.save(mockupEntity);
		} else {
			throw new OracleSQLException();
		}
	}

	public MockupEntity get(Long mockupId, UserInfo userInfo) {
		MockupEntity mockupEntity = null;
		try {
			mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId,
					userService.getUser(userInfo).getId(), null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		if (ObjectUtils.isNotEmpty(mockupEntity)) {
			return mockupEntity;
		} else {
			throw new NotFoundException("Mockup not exist");
		}
	}

	public MockupEntity edit(Long mockupId, UserInfo userInfo, MockupEntity mockupRequest) throws OracleSQLException {
		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e1) {
			e1.printStackTrace();
		}
		
		MockupEntity mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId, userId, null);
		if (ObjectUtils.isNotEmpty(mockupEntity)) {
			mockupEntity.setUserId(userId);
			try {
				mockupEntity.setFilePath(CreateGoogleFile.uploadMockupGoogleDrive(mockupRequest.getFilePath()));
			} catch (IOException e) {
				e.printStackTrace();
			}
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

	public String delete(Long mockupId, UserInfo userInfo) throws OracleSQLException {
		MockupEntity mockupEntity = new MockupEntity();
		try {
			mockupEntity = mockupRepository.findByIdAndUserIdAndDeletedAt(mockupId,
					userService.getUser(userInfo).getId(), null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
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
