package com.asia.leadsgen.test.service;

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.entity.ClipartEntity;
import com.asia.leadsgen.test.repository.ClipartRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class ClipartService {

	@Autowired
	ClipartRepository clipartRepository;

	public ClipartEntity create(ClipartEntity clipartEntity, long userId) throws OracleSQLException {
		clipartEntity.setUserId(userId);
		clipartEntity.setType("dropdown");
		clipartEntity.setCreatedAt(new Date());
		if (ObjectUtils.isNotEmpty(clipartRepository.save(clipartEntity))) {
			return clipartRepository.save(clipartEntity);
		} else {
			throw new OracleSQLException();
		}
	}
}
