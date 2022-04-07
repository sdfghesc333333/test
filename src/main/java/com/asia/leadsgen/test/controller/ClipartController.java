package com.asia.leadsgen.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

//@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/cliparts")
public class ClipartController {

	protected long userId = 4075;

	@Autowired
	ClipartRepository clipartRepository;

	@Autowired
	ClipartService clipartService;

//	Route::get('/cliparts', [ClipartController::class, 'list']);
	@GetMapping()
	public ResponseEntity<Page<ClipartEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) {
		Page<ClipartEntity> clipartnEntity = clipartRepository
				.findAllByUserId(PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()), userId);
		return new ResponseEntity<>(clipartnEntity, HttpStatus.OK);
	}

//	Route::post('/clipart', [ClipartController::class, 'create']);
	@PostMapping()
	public ResponseEntity<ClipartEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntity clipartRequest) {

		return new ResponseEntity<>(clipartService.createClipart(clipartRequest, userId), HttpStatus.OK);
	}
}
