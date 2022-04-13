package com.asia.leadsgen.test.controller;

import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.asia.leadsgen.test.model.entity.ProductOptionEntity;
import com.asia.leadsgen.test.repository.ProductOptionRepository;
import com.asia.leadsgen.test.service.ProductOptionService;
import com.asia.leadsgen.test.service.UserService;

import oracle.jdbc.driver.OracleSQLException;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "campaigns/{campaign_id}/options")
public class ProductOptionController {
	@Autowired
	ProductOptionService productOptionService;

	@Autowired
	ProductOptionRepository productOptionRepository;

	@Autowired
	UserService userService;

//	Route::get('/campaigns/{campaign_id}/options', [ProductOptionController::class, 'list']);
	@GetMapping()
	public ResponseEntity<Page<ProductOptionEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize,
			@PathVariable(name = "campaign_id") Long campaignId) throws LoginException {
//		logger.info(campaignId);
		Page<ProductOptionEntity> productOptionEntity = productOptionRepository.findAllByUserIdAndCampaignId(
				PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()),
				userService.getUser(userInfo).getId(), campaignId);
		return new ResponseEntity<>(productOptionEntity, HttpStatus.OK);
	}

//	Route::post('/campaigns/{campaign_id}/options', [ProductOptionController::class, 'create']);
	@PostMapping()
	public ResponseEntity<ProductOptionEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @RequestBody ProductOptionEntity productOptionEntity)
			throws LoginException, OracleSQLException {
		logger.info("======================== " + productOptionEntity);
		logger.info("user_info " + userInfo);

		return new ResponseEntity<>(productOptionService.createProductOption(productOptionEntity,
				userService.getUser(userInfo).getId(), campaignId), HttpStatus.OK);
	}

//	Route::put('/campaigns/{campaign_id}/options/{option_id}', [ProductOptionController::class, 'updateOption']);
	@PutMapping("/{option_id}")
	public ResponseEntity<ProductOptionEntity> update(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @PathVariable(name = "option_id") Long optionId,
			@RequestBody ProductOptionEntity productOptionEntity) throws LoginException, OracleSQLException {
		logger.info("======================== " + productOptionEntity);
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(productOptionService.updateOption(productOptionEntity,
				userService.getUser(userInfo).getId(), campaignId, optionId), HttpStatus.OK);
	}

	private Logger logger = Logger.getLogger(ProductOptionController.class.getName());
}
