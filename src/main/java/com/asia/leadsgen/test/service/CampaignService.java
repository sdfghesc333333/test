package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class CampaignService {

	@Autowired
	CampaignRepository campaignRepository;

	public CampaignEntity create(CampaignEntity campaignEntity, long userId) throws OracleSQLException {
		campaignEntity.setUserId(userId);
		campaignEntity.setStatus(1);
		campaignEntity.setCreatedAt(new Date());
		campaignEntity.setExportFileType("png");
		logger.info("save : " + campaignRepository.save(campaignEntity));
		if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignEntity))) {
			return campaignRepository.save(campaignEntity);
		} else {
			throw new OracleSQLException();
		}
	}

	public CampaignEntity updateCampaign(CampaignEntity campaignRequest, long userId, Long campaignId)
			throws OracleSQLException {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1,
				null);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			campaignEntity.setProductId(campaignRequest.getProductId());
			campaignEntity.setHandle(campaignRequest.getHandle());
			campaignEntity.setName(campaignRequest.getName());
			campaignEntity.setUpdatedAt(new Date());
			campaignEntity.setExportFileType("png");
			if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignEntity))) {
				return campaignRepository.save(campaignEntity);
			} else {
				throw new OracleSQLException();
			}
		} else {
			throw new NotFoundException("Campaign not exist");
		}
	}

	public String deleteCampaign(long campaignId, long userId) throws OracleSQLException {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1,
				null);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			campaignEntity.setStatus(0);
			campaignEntity.setDeletedAt(new Date());
			if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignEntity))) {
				campaignRepository.save(campaignEntity);
				return "msg : Success";
			} else {
				throw new OracleSQLException();
			}
		} else {
			throw new NotFoundException("Campaign not exist");
		}
	}

	public CampaignEntity getCampaign(Long campaignId, Long userId) {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1,
				null);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			return campaignEntity;
		} else {
			throw new NotFoundException("Campaign not exist");
		}
	}

	private Logger logger = Logger.getLogger(CampaignService.class.getName());
}
