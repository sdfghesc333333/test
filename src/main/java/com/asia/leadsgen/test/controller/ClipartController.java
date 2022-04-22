package com.asia.leadsgen.test.controller;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.asia.leadsgen.test.model.entity.ClipartEntityResponse;
import com.asia.leadsgen.test.service.ClipartService;
import com.asia.leadsgen.test.util.AppParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oracle.jdbc.driver.OracleSQLException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/cliparts")
public class ClipartController {

	@Autowired
	ClipartService clipartService;

//	Route::get('/cliparts', [ClipartController::class, 'list']);
	@GetMapping()
	@Operation(summary = "List cliparts")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<List<ClipartEntityResponse>> list(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "15") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "created_at") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) throws LoginException {
		List<ClipartEntityResponse> clipartnEntities = clipartService.list(page, pageSize, search, startDate, endDate,
				sort, dir, userInfo);
		return new ResponseEntity<>(clipartnEntities, HttpStatus.OK);
	}

//	Route::post('/clipart', [ClipartController::class, 'create']);
	@PostMapping()
	@Operation(summary = "Create clipart")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<ClipartEntityResponse> create(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntityResponse clipartRequest) throws OracleSQLException {

		return new ResponseEntity<>(clipartService.createOrUpdate(clipartRequest, userInfo, null), HttpStatus.OK);
	}

//	Route::put('/clipart/{cat_id}', [ClipartController::class, 'create']);
	@PutMapping("/{cat_id}")
	@Operation(summary = "Update clipart")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<ClipartEntityResponse> update(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntityResponse clipartRequest, @PathVariable(name = "cat_id") Long catId)
			throws OracleSQLException {

		return new ResponseEntity<>(clipartService.createOrUpdate(clipartRequest, userInfo, catId), HttpStatus.OK);
	}

//	Route::delete('/clipart/{cat_id}', [ClipartController::class, 'delete']);
	@DeleteMapping("/{cat_id}")
	@Operation(summary = "Delete clipart")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<String> delete(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody ClipartEntityResponse clipartRequest, @PathVariable(name = "cat_id") Long catId)
			throws OracleSQLException {

		return new ResponseEntity<>(clipartService.delete(userInfo, catId), HttpStatus.OK);
	}

}
