package com.asia.leadsgen.test.controller;

import java.text.ParseException;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.service.UserService;
import com.asia.leadsgen.test.util.AppParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/stats")
public class HomeController {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	UserService userService;

//	Route::get('/stats', [HomeController::class, 'stats']);
	@GetMapping()
	@Operation(summary = "Count")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<Map<String, Integer>> stats(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) /*
																										 * @ApiParam(
																										 * value =
																										 * "Access Token"
																										 * , example =
																										 * AppParams.
																										 * TOKEN)
																										 */ String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo)
			throws LoginException, ParseException {

		Map<String, Integer> result = new HashedMap<String, Integer>();
		result.put("total_campaigns ", campaignRepository.countTotal30dayago(userService.getUser(userInfo).getId()));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
