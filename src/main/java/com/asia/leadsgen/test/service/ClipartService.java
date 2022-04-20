package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.ClipartEntityResponse;
import com.asia.leadsgen.test.repository.ClipartRepository;
import com.asia.leadsgen.test.util.DateTimeUtil;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class ClipartService {

	@Autowired
	ClipartRepository clipartRepository;

	@Autowired
	UserService userService;

	public ClipartEntityResponse createOrUpdate(ClipartEntityResponse clipartEntity, UserInfo userInfo, Long catId)
			throws OracleSQLException {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		try {
			clipartEntity.setThumbnail(CreateGoogleFile.uploadFileImage(clipartEntity.getThumbnail()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("===================================================");
		
//		clipartEntity.setClipartCategories(clipartEntity.getClipartCategories().toString());
//		System.out.print(clipartEntity.getClipartCategories().toString());

		if (catId == null) {
			clipartEntity.setUserId(userId);
			clipartEntity.setType("dropdown");
			clipartEntity.setCreatedAt(new Date());
//			clipartEntity.setClipartCategories("");
			if (ObjectUtils.isNotEmpty(clipartRepository.save(clipartEntity))) {
				return clipartRepository.save(clipartEntity);
			} else {
				throw new OracleSQLException();
			}
		} else {
			ClipartEntityResponse entity = clipartRepository.getById(userId, catId);
			entity.setClipartCategories(clipartEntity.getClipartCategories());
			entity.setName(clipartEntity.getName());
			entity.setThumbnail(clipartEntity.getThumbnail());
			entity.setType(clipartEntity.getType());
			entity.setUpdatedAt(new Date());
			if (ObjectUtils.isNotEmpty(clipartRepository.save(clipartEntity))) {
				return clipartRepository.save(entity);
			} else {
				throw new OracleSQLException();
			}
		}

	}

	public List<ClipartEntityResponse> list(int page, int pageSize, String startDate, String endDate, String sort, String dir,
			UserInfo userInfo) {
		List<ClipartEntityResponse> clipartEntity;
		Pageable pageable;

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (dir.equals("asc")) {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).ascending());
		} else {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).descending());
		}

		clipartEntity = clipartRepository.list(pageable, userId, DateTimeUtil.startDateFomat(startDate),
				DateTimeUtil.endDateFomat(endDate));

		return clipartEntity;
	}

}
