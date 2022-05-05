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

import com.asia.leadsgen.test.model.SortOptionRequestModel;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.ProductOptionEntity;
import com.asia.leadsgen.test.model.request.ProductOptionRequest;
import com.asia.leadsgen.test.service.ProductOptionService;
import com.asia.leadsgen.test.util.AppParams;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import oracle.jdbc.driver.OracleSQLException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "campaigns/{campaign_id}/options")
public class ProductOptionController {
	@Autowired
	ProductOptionService productOptionService;

//	Route::get('/campaigns/{campaign_id}/options', [ProductOptionController::class, 'list']);
	@GetMapping()
	@Operation(summary = "List Products")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<Page<ProductOptionEntity>> list(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@PathVariable(name = "campaign_id") Long campaignId,
			@RequestParam(name = "sort", defaultValue = "createdAt") String sort,
			@RequestParam(name = "dir", defaultValue = "desc") String dir) {
		Page<ProductOptionEntity> productOptionEntity = productOptionService.list(campaignId, page, pageSize, startDate,
				endDate, sort, dir, userInfo);
		return new ResponseEntity<>(productOptionEntity, HttpStatus.OK);
	}

//	Route::post('/campaigns/{campaign_id}/options', [ProductOptionController::class, 'create']);
	@PostMapping()
	@Operation(summary = "Create Product")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<ProductOptionEntity> create(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @RequestBody ProductOptionRequest productOptionRequest)
			throws OracleSQLException {
		logger.info("======================== " + productOptionRequest);
		logger.info("user_info " + userInfo);

		return new ResponseEntity<>(
				productOptionService.createProductOption(productOptionRequest, userInfo, campaignId), HttpStatus.OK);
	}

//	Route::put('/campaigns/{campaign_id}/options/{option_id}', [ProductOptionController::class, 'updateOption']);
	@PutMapping("/{option_id}")
	@Operation(summary = "Update")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<ProductOptionEntity> update(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @PathVariable(name = "option_id") Long optionId,
			@RequestBody ProductOptionRequest productOptionRequest) throws OracleSQLException {
		logger.info("======================== " + productOptionRequest);
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(
				productOptionService.updateOption(productOptionRequest, userInfo, campaignId, optionId), HttpStatus.OK);
	}

//	Route::post('/campaigns/{campaign_id}/options/sort', [ProductOptionController::class, 'sortOptions']);
	@PostMapping("/sort")
	@Operation(summary = "Sort Options")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<String> sortOptions(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @RequestBody SortOptionRequestModel optionIds)
			throws OracleSQLException {
		logger.info("======================== " + optionIds);
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(productOptionService.sortOptions(optionIds, userInfo, campaignId), HttpStatus.OK);
	}

//	Route::delete('/campaigns/{campaign_id}/options/{option_id}', [ProductOptionController::class, 'delete']);
	@DeleteMapping("/{option_id}")
	@Operation(summary = "Update")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
			@ApiResponse(responseCode = "201", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
			@ApiResponse(responseCode = "403", description = "Not implement", content = @Content),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Sever Error", content = @Content) })
	public ResponseEntity<String> delete(
			@RequestHeader(name = "x-authorization", required = true, defaultValue = AppParams.TOKEN) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @PathVariable(name = "option_id") Long optionId,
			@RequestBody ProductOptionEntity productOptionEntity) throws OracleSQLException {
		logger.info("======================== " + productOptionEntity);
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(productOptionService.deleteOption(optionId, userInfo, campaignId), HttpStatus.OK);
	}

	private Logger logger = Logger.getLogger(ProductOptionController.class.getName());
}
