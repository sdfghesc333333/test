package com.asia.leadsgen.test.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.asia.leadsgen.test.model.entity.ClipartEntityResponse;
import com.asia.leadsgen.test.repository.ClipartRepository;
import com.asia.leadsgen.test.service.ClipartService;

import oracle.jdbc.driver.OracleSQLException;

//@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/cliparts")
public class ClipartController {

	@Autowired
	ClipartService clipartService;

	@Autowired
	ClipartRepository clipartRepository;

//	Route::get('/cliparts', [ClipartController::class, 'list']);
	@GetMapping()
	public ResponseEntity<List<ClipartEntityResponse>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "created_at") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) throws LoginException {
		List<ClipartEntityResponse> clipartnEntities = clipartService.list(page, pageSize, startDate, endDate, sort, dir,
				userInfo);
		return new ResponseEntity<>(clipartnEntities, HttpStatus.OK);
	}

	@GetMapping("/id")
	public ResponseEntity<List<ClipartEntityResponse>> show(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "created_at") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) throws LoginException {
		List<ClipartEntityResponse> clipartnEntities = clipartService.list(page, pageSize, startDate, endDate, sort, dir,
				userInfo);
		return new ResponseEntity<>(clipartnEntities, HttpStatus.OK);
	}

//	Route::post('/clipart', [ClipartController::class, 'create']);
	@PostMapping()
	public ResponseEntity<ClipartEntityResponse> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntityResponse clipartRequest) throws OracleSQLException {

		return new ResponseEntity<>(clipartService.createOrUpdate(clipartRequest, userInfo, null), HttpStatus.OK);
	}
}
