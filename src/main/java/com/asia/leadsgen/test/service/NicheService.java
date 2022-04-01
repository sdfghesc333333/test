package com.asia.leadsgen.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.exception.OracleException;
import com.asia.leadsgen.test.model.NicheInfo;
import com.asia.leadsgen.test.repository.NicheRepository;
import com.asia.leadsgen.test.util.GenerateStringUtils;

@Service
public class NicheService {
	@Autowired
	NicheRepository nicheRepository;

	@Autowired
	GenerateStringUtils generateStringUtils;

	public List<NicheInfo> createNiche(List<NicheInfo> niches) {
		List<NicheInfo> dataList = new ArrayList<>();
		for (NicheInfo data : niches) {
			NicheInfo result = new NicheInfo();

			Date date = new Date();
			String id = generateStringUtils.generateId();
			String name = data.getName();
			String type = data.getType();

			result.setId(id);
			result.setName(name);
			result.setType(type);
			result.setCreate(date);

			if (!nicheRepository.existsByName(name)) {
				dataList.add(result);
//				throw new OracleException("Niche " + name + " already exists");
			}
		}
		List<NicheInfo> results = nicheRepository.saveAllAndFlush(dataList);
		LOGGER.info("result " + results);
		return results;
	}

	public Map<String, Object> listNiches(int page, int size, String name) {
		List<NicheInfo> niches = new ArrayList<NicheInfo>();
		String state = "approved";
		Pageable paging = PageRequest.of(page - 1, size, Sort.by("name"));
		Page<NicheInfo> result;
		if (name == null || name.isEmpty()) {
			result = nicheRepository.findByState(state, paging);
		} else {
			String formatName = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
			result = nicheRepository.findByNameAndState(formatName, state, paging);
		}
		niches = result.getContent();
		Map<String, Object> response = new HashMap<>();
		response.put("total_pages", result.getTotalPages());
		response.put("total_items", result.getTotalElements());
		response.put("niches", niches);
		response.put("current_page", result.getNumber() + 1);
		return response;
	}

	private static final Logger LOGGER = Logger.getLogger(NicheService.class.getName());
}
