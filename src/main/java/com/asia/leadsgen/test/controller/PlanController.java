package com.asia.leadsgen.test.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.response.ServicePackResponse;
import com.asia.leadsgen.test.repository.impl.ServicePackRepositoryImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "plans")
public class PlanController {

	@Autowired
	ServicePackRepositoryImpl servicePackRepositoryImpl;

	@GetMapping()
	public ResponseEntity<List<ServicePackResponse>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) throws LoginException {

		return new ResponseEntity<>(servicePackRepositoryImpl.list(), HttpStatus.OK);
	}
}