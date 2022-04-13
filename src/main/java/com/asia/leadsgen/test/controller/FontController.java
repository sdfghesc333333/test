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
import com.asia.leadsgen.test.model.entity.FontEntity;
import com.asia.leadsgen.test.repository.FontRepository;
import com.asia.leadsgen.test.repository.UserRepository;
import com.asia.leadsgen.test.service.FontService;

//@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/fonts")
public class FontController {
	@Autowired
	FontRepository fontRepository;

	@Autowired
	FontService fontService;

	@Autowired
	UserRepository userRepository;

//	Route::get('/fonts', [FontController::class, 'list']);
	@GetMapping()
	public ResponseEntity<Page<FontEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) {

		Page<FontEntity> fontEntity = fontRepository.findAllByUserId(
				PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()),
				userRepository.findByAffId(userInfo.getUserId()).getId());
		return new ResponseEntity<>(fontEntity, HttpStatus.OK);
	}

//	Route::post('/fonts', [FontController::class, 'create']);
	@PostMapping()
	public ResponseEntity<FontEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody FontEntity fontEntity) throws IOException {

		return new ResponseEntity<>(
				fontService.createFont(fontEntity, userRepository.findByAffId(userInfo.getUserId()).getId()),
				HttpStatus.OK);
	}
}
