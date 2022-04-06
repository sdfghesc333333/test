package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.ProductOptionEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.repository.ProductOptionRepository;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ProductOptionService {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	ProductOptionRepository productOptionRepository;

	public Map createProductOption(ProductOptionEntity productOptionEntity, long userId, long campaignId) {

		Map result = new HashMap<>();

		if (ObjectUtils.isEmpty(campaignRepository.findByIdAndUserIdAndStatus(campaignId, userId, 1))) {
			result.put("errors", "Campaign not exist!");
		} else {
			productOptionEntity.setUserId(userId);
			productOptionEntity.setCampaignId(campaignId);
			productOptionEntity.setCreatedAt(new Date());
			result.put("data", productOptionRepository.save(productOptionEntity));
		}
		return result;
	}

	public Map updateOption(ProductOptionEntity productOptionEntity, long userId, long campaignId, long optionId) {
		Map result = new HashMap<>();
		if (ObjectUtils.isEmpty(productOptionRepository.findByIdAndUserIdAndCampaignId(optionId, userId, campaignId))) {
			result.put("errors", "Option not exist!");
		} else {
			ProductOptionEntity optionEntity = productOptionRepository.findByIdAndUserIdAndCampaignId(optionId, userId,
					campaignId);
			optionEntity.setType(productOptionEntity.getType());
			optionEntity.setTitle(productOptionEntity.getTitle());
			optionEntity.setUpdatedAt(new Date());

			logger.info("product option : " + optionEntity);

			if (ObjectUtils.isNotEmpty(productOptionRepository.save(optionEntity))) {
				result.put("msg", "Success");
			}
		}
		return result;
	}

	private Logger logger = Logger.getLogger(ProductOptionService.class.getName());
}
