package com.asia.leadsgen.test.controller;

import java.io.IOException;

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
import com.asia.leadsgen.test.model.entity.MockupEntity;
import com.asia.leadsgen.test.repository.MockupRepository;
import com.asia.leadsgen.test.repository.UserRepository;
import com.asia.leadsgen.test.service.MockupService;

//@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/mockups")
public class MockupController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MockupRepository mockupRepository;

	@Autowired
	MockupService mockupService;
	
//	Route::get('/mockups', [MockupController::class, 'list']);
	@GetMapping()
	public ResponseEntity<Page<MockupEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) {

		Page<MockupEntity> mockupEntity = mockupRepository.findAllByUserIdAndDeletedAt(
				PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()),
				userRepository.findByAffIdAndDeletedAt(userInfo.getUserId(), null).getId(), null);
		return new ResponseEntity<>(mockupEntity, HttpStatus.OK);
	}

//	Route::post('/mockups', [MockupController::class, 'add']);
	@PostMapping()
	public ResponseEntity<MockupEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody MockupEntity mockupEntity) throws IOException {

		return new ResponseEntity<>(
				mockupService.createMockup(mockupEntity, userRepository.findByAffIdAndDeletedAt(userInfo.getUserId(), null).getId()),
				HttpStatus.OK);
	}
}
