package com.asia.leadsgen.test.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.test.model.CampaignEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/campaigns")
public class CampaignController {
	@Autowired
	CampaignRepository campaignRepository;

	@GetMapping()
	public ResponseEntity<Page<CampaignEntity>> list() {
		PageRequest pageable = PageRequest.of(2, 30);
		Page<CampaignEntity> result = new PageImpl<CampaignEntity>(campaignRepository.findAll(), pageable, campaignRepository.findAll().size());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
