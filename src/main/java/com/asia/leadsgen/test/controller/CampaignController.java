package com.asia.leadsgen.test.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.service.CampaignService;
import com.asia.leadsgen.test.util.AppParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oracle.jdbc.driver.OracleSQLException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/campaigns")
public class CampaignController {

	@Autowired
	CampaignService campaignService;

//	Route::get('/campaigns', [CampaignController::class, 'list']);
	@GetMapping()
	@Operation(summary = "List campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<Page<CampaignEntity>> list(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) /*
																										 * @ApiParam(
																										 * value =
																										 * "Access Token"
																										 * , example =
																										 * AppParams.
																										 * TOKEN)
																										 */ String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "search", required = false) String search,
			@RequestParam(name = "sort", defaultValue = "created_at") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) {

		Page<CampaignEntity> campaignEntity = campaignService.list(page, pageSize, startDate, endDate, search, sort,
				dir, userInfo);
		return new ResponseEntity<>(campaignEntity, HttpStatus.OK);
	}

//	Route::post('/campaigns', [CampaignController::class, 'create']);
	@PostMapping()
	@Operation(summary = "Create campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<CampaignEntity> create(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody CampaignEntity campaignRequest) throws OracleSQLException {
		logger.info("user_info " + userInfo);

		return new ResponseEntity<>(campaignService.create(campaignRequest, userInfo), HttpStatus.OK);
	}

//	Route::get('/campaigns/{campaign_id}', [CampaignController::class, 'getCampaign']);
	@GetMapping("/{campaign_id}")
	@Operation(summary = "Get campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<CampaignEntity> getCampaign(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId) {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(campaignService.getCampaign(campaignId, userInfo), HttpStatus.OK);
	}

	@PutMapping("/{campaign_id}")
	@Operation(summary = "Update campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<CampaignEntity> updateCampaign(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @RequestBody CampaignEntity campaignRequest)
			throws OracleSQLException {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(campaignService.updateCampaign(campaignRequest, userInfo, campaignId),
				HttpStatus.OK);
	}

//	Route::delete('/campaigns/{campaign_id}', [CampaignController::class, 'deleteCampaign']);
	@DeleteMapping("/{campaign_id}")
	@Operation(summary = "Delete campaigns")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<String> deleteCampaign(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId) throws OracleSQLException {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(campaignService.deleteCampaign(campaignId, userInfo), HttpStatus.OK);
	}

	private Logger logger = Logger.getLogger(CampaignController.class.getName());
}
