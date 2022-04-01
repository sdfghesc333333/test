package com.asia.leadsgen.fmerch.controller;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asia.leadsgen.fmerch.model.NicheInfo;
import com.asia.leadsgen.fmerch.service.NicheService;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "/niches")
public class NicheController {

	@Autowired
	NicheService nicheService;

	@PostMapping()
	public ResponseEntity<List<NicheInfo>> createNiche(@RequestBody List<NicheInfo> niches) {
		List<NicheInfo> result = nicheService.createNiche(niches);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@GetMapping()
	public ResponseEntity<Map<String, Object>> listNiche(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(required = false) String name) {
		Map<String, Object> result = nicheService.listNiches(page, size, name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	private Logger logger = Logger.getLogger(NicheController.class.getName());
}
