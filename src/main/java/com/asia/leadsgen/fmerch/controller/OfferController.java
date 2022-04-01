package com.asia.leadsgen.fmerch.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.fmerch.model.FmerchCampaignInfo;
import com.asia.leadsgen.fmerch.model.OfferInfo;
import com.asia.leadsgen.fmerch.model.ResultObjectInfo;
import com.asia.leadsgen.fmerch.model.UserInfo;
import com.asia.leadsgen.fmerch.model.response.FmerchCampaignResponse;
import com.asia.leadsgen.fmerch.service.OfferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/offers")
public class OfferController {
	@Autowired
	OfferService offerService;

	@GetMapping("/{id}")
	public ResponseEntity<FmerchCampaignInfo> getDetailOffer(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo, @PathVariable String id) {
		FmerchCampaignInfo result = offerService.getOfferById(id, userInfo.getUserId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<OfferInfo> createOffer(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo, @RequestBody OfferInfo offerInfo)
			throws SQLException {

		OfferInfo result = offerService.createOffer(userInfo, offerInfo);

		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping("/v2")
	@Operation(summary = "List Offers v2")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK", content = @Content),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<ResultObjectInfo<FmerchCampaignResponse>> getOfferv3(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "50") int pageSize,
			@RequestParam(name = "status", required = false) String status,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "payout") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) throws SQLException, ParseException {

		logger.info("User Info: " + userInfo);

		ResultObjectInfo<FmerchCampaignResponse> resultObject = offerService.listOfferv2(userInfo.getUserId(), page,
				pageSize, status, startDate, endDate, search, sort, dir);

		return new ResponseEntity<>(resultObject, HttpStatus.OK);
	}

	private Logger logger = Logger.getLogger(SimpleController.class.getName());
}
