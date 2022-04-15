package com.asia.leadsgen.test.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.ClipartEntity;
import com.asia.leadsgen.test.repository.ClipartRepository;
import com.asia.leadsgen.test.service.ClipartService;
import com.asia.leadsgen.test.service.UserService;

import oracle.jdbc.driver.OracleSQLException;

//@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/cliparts")
public class ClipartController {

	@Autowired
	ClipartRepository clipartRepository;

	@Autowired
	ClipartService clipartService;

	@Autowired
	UserService userService;

//	Route::get('/cliparts', [ClipartController::class, 'list']);
	@GetMapping()
	public ResponseEntity<List<ClipartEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) throws LoginException {
		List<ClipartEntity> clipartnEntities = clipartRepository.list(
				PageRequest.of(page - 1, pageSize, Sort.by("created_at").descending()),
				userService.getUser(userInfo).getId());
		return new ResponseEntity<>(clipartnEntities, HttpStatus.OK);
	}

//	Route::post('/clipart', [ClipartController::class, 'create']);
	@PostMapping()
	public ResponseEntity<ClipartEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntity clipartRequest) throws LoginException, OracleSQLException {

		return new ResponseEntity<>(clipartService.create(clipartRequest, userService.getUser(userInfo).getId()),
				HttpStatus.OK);
	}
}
