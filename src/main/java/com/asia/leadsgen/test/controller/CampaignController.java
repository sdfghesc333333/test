package com.asia.leadsgen.test.controller;

import java.util.Map;
import java.util.logging.Logger;

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
import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.repository.UserRepository;
import com.asia.leadsgen.test.service.CampaignService;

@SuppressWarnings("rawtypes")
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/campaigns")
public class CampaignController {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	CampaignService campaignService;

	@Autowired
	UserRepository userRepository;

//	Route::get('/campaigns', [CampaignController::class, 'list']);
	@GetMapping()
	public ResponseEntity<Page<CampaignEntity>> list(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "page_size", defaultValue = "10") int pageSize) {

		logger.info("user_info " + userRepository.findByAffId(userInfo.getUserId()).getId());
		Page<CampaignEntity> campaignEntity = campaignRepository.findAllByUserId(
				PageRequest.of(page - 1, pageSize, Sort.by("createdAt").descending()),
				userRepository.findByAffId(userInfo.getUserId()).getId());
		return new ResponseEntity<>(campaignEntity, HttpStatus.OK);
	}

//	Route::post('/campaigns', [CampaignController::class, 'create']);
	@PostMapping()
	public ResponseEntity<CampaignEntity> create(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@RequestBody CampaignEntity campaignRequest) {
		logger.info("user_info " + userInfo);

		return new ResponseEntity<>(campaignService.createCampaign(campaignRequest,
				userRepository.findByAffId(userInfo.getUserId()).getId()), HttpStatus.OK);
	}

//	Route::get('/campaigns/{campaign_id}', [CampaignController::class, 'getCampaign']);
	@GetMapping("/{campaign_id}")
	public ResponseEntity<Map> getCampaign(@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId) {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(
				campaignService.getCampaign(campaignId, userRepository.findByAffId(userInfo.getUserId()).getId()),
				HttpStatus.OK);
	}

//	Route::put('/campaigns/{campaign_id}', [CampaignController::class, 'updateCampaign']);
	@PutMapping("/{campaign_id}")
	public ResponseEntity<Map> updateCampaign(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId, @RequestBody CampaignEntity campaignRequest) {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(campaignService.updateCampaign(campaignRequest,
				userRepository.findByAffId(userInfo.getUserId()).getId(), campaignId), HttpStatus.OK);
	}

//	Route::delete('/campaigns/{campaign_id}', [CampaignController::class, 'deleteCampaign']);
	@DeleteMapping("/{campaign_id}")
	public ResponseEntity<Map> deleteCampaign(
			@RequestHeader(name = "x-authorization", required = true) String accessToken,
			@RequestAttribute(name = "user_info", required = true) UserInfo userInfo,
			@PathVariable(name = "campaign_id") Long campaignId) {
		logger.info("user_info " + userInfo);
		return new ResponseEntity<>(
				campaignService.deleteCampaign(campaignId, userRepository.findByAffId(userInfo.getUserId()).getId()),
				HttpStatus.OK);
	}

	private Logger logger = Logger.getLogger(CampaignController.class.getName());
}
