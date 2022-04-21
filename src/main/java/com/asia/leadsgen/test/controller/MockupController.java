package com.asia.leadsgen.test.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.MockupEntity;
import com.asia.leadsgen.test.repository.MockupRepository;
import com.asia.leadsgen.test.service.MockupService;
import com.asia.leadsgen.test.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oracle.jdbc.driver.OracleSQLException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/mockups")
public class MockupController {

	@Autowired
	UserService userService;

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
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) throws LoginException {

		Page<MockupEntity> mockupEntity = mockupRepository.findAllByUserIdAndDeletedAt(
				PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()),
				userService.getUser(userInfo).getId(), null);
		return new ResponseEntity<>(mockupEntity, HttpStatus.OK);
	}

//	Route::post('/mockups', [MockupController::class, 'add']);
	@PostMapping()
	public ResponseEntity<MockupEntity> add(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody MockupEntity mockupEntity) throws OracleSQLException {

		return new ResponseEntity<>(mockupService.add(mockupEntity, userInfo), HttpStatus.OK);
	}

//	Route::get('/mockups/{mockup_id}', [MockupController::class, 'get']);
	@GetMapping("/{mockup_id}")
	public ResponseEntity<MockupEntity> get(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "mockup_id") Long mockupId) {
		return new ResponseEntity<>(mockupService.get(mockupId, userInfo), HttpStatus.OK);
	}

//	Route::put('/mockups/{mockup_id}', [MockupController::class, 'edit']);
	@PutMapping("/{mockup_id}")
	public ResponseEntity<MockupEntity> edit(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "mockup_id") Long mockupId, @RequestBody MockupEntity mockupRequest)
			throws OracleSQLException {
		return new ResponseEntity<>(mockupService.edit(mockupId, userInfo, mockupRequest), HttpStatus.OK);
	}

//	Route::delete('/mockups/{mockup_id}', [MockupController::class, 'delete']);
	@DeleteMapping("/{mockup_id}")
	public ResponseEntity<String> delete(@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "mockup_id") Long mockupId) throws OracleSQLException {
		return new ResponseEntity<>(mockupService.delete(mockupId, userInfo), HttpStatus.OK);
	}

//	Route::get('/mockupsForListPage', [MockupController::class, 'getListForMockupPage']);
	@GetMapping("/mockupsForListPage")
	@Operation(summary = "List campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<Page<MockupEntity>> getListForMockupPage(
			@RequestHeader(name = "x-authorization", required = true) /*@ApiParam(value = "Access Token", example = AppParams.TOKEN)*/ String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "createdAt") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) {

		Page<MockupEntity> mockupEntity = mockupService.getListForMockupPage(page, pageSize, startDate, endDate, search,
				sort, dir, userInfo);
		return new ResponseEntity<>(mockupEntity, HttpStatus.OK);
	}

}
