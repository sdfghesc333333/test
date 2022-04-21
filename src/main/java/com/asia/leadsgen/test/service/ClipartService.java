package com.asia.leadsgen.test.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.drivecloud.CreateGoogleFile;
import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.ClipartEntityResponse;
import com.asia.leadsgen.test.repository.ClipartRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class ClipartService {

	@Autowired
	ClipartRepository clipartRepository;

	@Autowired
	UserService userService;

	public List<ClipartEntityResponse> list(int page, int pageSize, String startDate, String endDate, String search,
			String sort, String dir, UserInfo userInfo) {
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

		if (StringUtils.isEmpty(search)) {
			clipartEntity = clipartRepository.list(pageable, userId);
		} else {
			clipartEntity = clipartRepository.listLikeName(pageable, userId, "%" + search + "%");
		}

		return clipartEntity;
	}

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

		if (catId == null) {
			clipartEntity.setUserId(userId);
			clipartEntity.setType("dropdown");
			clipartEntity.setCreatedAt(new Date());
			if (ObjectUtils.isNotEmpty(clipartRepository.save(clipartEntity))) {
				return clipartRepository.save(clipartEntity);
			} else {
				throw new OracleSQLException();
			}
		} else {
			ClipartEntityResponse entity = clipartRepository.getById(userId, catId);
			if (ObjectUtils.isNotEmpty(catId)) {
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
			} else {
				throw new NotFoundException("Cat not exist");
			}
		}

	}

	public String delete(UserInfo userInfo, Long catId) throws OracleSQLException {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		ClipartEntityResponse entity = clipartRepository.getById(userId, catId);

		if (ObjectUtils.isNotEmpty(entity)) {
			entity.setDeletedAt(new Date());
			if (ObjectUtils.isNotEmpty(clipartRepository.save(entity))) {
				clipartRepository.save(entity);
				return "msg : Success";
			} else {
				throw new OracleSQLException();
			}
		} else {
			return "errors : Cat not exist";
		}
	}

}
