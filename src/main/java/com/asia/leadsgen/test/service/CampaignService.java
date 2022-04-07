package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class CampaignService {

	@Autowired
	CampaignRepository campaignRepository;

	public CampaignEntity createCampaign(CampaignEntity campaignEntity, long userId) {
		campaignEntity.setUserId(userId);
		campaignEntity.setStatus(1);
		campaignEntity.setCreatedAt(new Date());
		campaignEntity.setExportFileType("png");
		logger.info("save : " + campaignRepository.save(campaignEntity));
		return campaignRepository.save(campaignEntity);
	}

	public Map updateCampaign(CampaignEntity campaignRequest, long userId, Long campaignId) {
		Map result = new HashMap<>();
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatus(campaignId, userId, 1);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			campaignEntity.setProductId(campaignRequest.getProductId());
			campaignEntity.setHandle(campaignRequest.getHandle());
			campaignEntity.setName(campaignRequest.getName());
			campaignEntity.setUpdatedAt(new Date());
			campaignEntity.setExportFileType("png");
			logger.info("campaignEntity " + campaignEntity);
			if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignEntity))) {
				result.put("msg", "Success");
			}
		} else {
			result.put("errors", "Campaign not exist");
		}
		return result;
	}

	public Map deleteCampaign(long campaignId, long userId) {
		Map result = new HashMap<>();
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatus(campaignId, userId, 1);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			campaignEntity.setStatus(0);
			campaignEntity.setDeletedAt(new Date());
			logger.info("campaignEntity " + campaignEntity);
			if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignEntity))) {
				result.put("msg", "Success");
			}
		} else {
			result.put("errors", "Campaign not exist");
		}
		return result;
	}

	public Map getCampaign(Long campaignId, Long userId) {
		Map result = new HashMap<>();
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatus(campaignId, userId, 1);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			result.put("data", campaignEntity);
		} else {
			result.put("errors", "Campaign not exist");
		}
		return result;
	}

	private Logger logger = Logger.getLogger(CampaignService.class.getName());
}
