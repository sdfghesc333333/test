package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.entity.ProductOptionEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.repository.ProductOptionRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class ProductOptionService {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	ProductOptionRepository productOptionRepository;

	public ProductOptionEntity createProductOption(ProductOptionEntity productOptionEntity, long userId,
			long campaignId) throws OracleSQLException {

		if (ObjectUtils
				.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			productOptionEntity.setUserId(userId);
			productOptionEntity.setCampaignId(campaignId);
			productOptionEntity.setCreatedAt(new Date());
			if (ObjectUtils.isNotEmpty(productOptionRepository.save(productOptionEntity))) {
				return productOptionRepository.save(productOptionEntity);
			} else {
				throw new OracleSQLException();
			}
		}
	}

	public ProductOptionEntity updateOption(ProductOptionEntity productOptionEntity, long userId, long campaignId,
			long optionId) throws OracleSQLException {
		
		if (ObjectUtils.isEmpty(productOptionRepository.findByIdAndUserIdAndCampaignId(optionId, userId, campaignId))) {
			throw new NotFoundException("Option not exist");
		} else {
			ProductOptionEntity optionEntity = productOptionRepository.findByIdAndUserIdAndCampaignId(optionId, userId,
					campaignId);
			optionEntity.setType(productOptionEntity.getType());
			optionEntity.setTitle(productOptionEntity.getTitle());
			optionEntity.setUpdatedAt(new Date());

			logger.info("product option : " + optionEntity);

			if (ObjectUtils.isNotEmpty(productOptionRepository.save(optionEntity))) {
				return productOptionRepository.save(optionEntity);
			} else {
				throw new OracleSQLException();
			}
		}
	}

	private Logger logger = Logger.getLogger(ProductOptionService.class.getName());
}
