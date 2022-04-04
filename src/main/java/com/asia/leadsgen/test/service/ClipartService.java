package com.asia.leadsgen.test.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.ClipartEntity;
import com.asia.leadsgen.test.repository.ClipartRepository;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ClipartService {

	@Autowired
	ClipartRepository clipartRepository;

	public ClipartEntity createClipart(ClipartEntity clipartEntity, long userId) {
		clipartEntity.setUserId(userId);
		clipartEntity.setType("dropdown");
		clipartEntity.setCreatedAt(new Date());
		return clipartRepository.save(clipartEntity);
	}
}
