package com.asia.leadsgen.test.service;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.UserEntity;
import com.asia.leadsgen.test.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public UserEntity getUser(UserInfo userInfo) throws LoginException {
		if(ObjectUtils.isNotEmpty(userRepository.findByAffIdAndDeletedAt(userInfo.getUserId(), null))) {
			return userRepository.findByAffIdAndDeletedAt(userInfo.getUserId(), null);
		}else {
			throw new LoginException();
		}
	}
}
