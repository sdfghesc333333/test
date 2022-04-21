package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.exception.NotFoundException;
import com.asia.leadsgen.test.model.UserInfo;
import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.util.DateTimeUtil;

import oracle.jdbc.driver.OracleSQLException;

@Service
public class CampaignService {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	UserService userService;

	public Page<CampaignEntity> list(int page, int pageSize, String startDate, String endDate, String search,
			String sort, String dir, UserInfo userInfo) {
		Page<CampaignEntity> campaignEntity;
		Pageable pageable;

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (dir.equals("asc")) {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).ascending());
		} else {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).descending());
		}

		if (StringUtils.isEmpty(search)) {
			campaignEntity = campaignRepository.findAllByUserIdAndDeletedAtAndCreatedAtBetween(pageable, userId, null,
					DateTimeUtil.startDateFomat(startDate), DateTimeUtil.endDateFomat(endDate));
		} else {
			campaignEntity = campaignRepository.findAllByUserIdAndDeletedAtAndNameLikeAndCreatedAtBetween(pageable,
					userId, null, "%" + search + "%", DateTimeUtil.startDateFomat(startDate),
					DateTimeUtil.endDateFomat(endDate));
		}

		return campaignEntity;
	}

	public CampaignEntity create(CampaignEntity campaignEntity, UserInfo userInfo) throws OracleSQLException {
		try {
			campaignEntity.setUserId(userService.getUser(userInfo).getId());
		} catch (LoginException e) {
			e.printStackTrace();
		}
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

	public CampaignEntity updateCampaign(CampaignEntity campaignRequest, UserInfo userInfo, Long campaignId)
			throws OracleSQLException {
		CampaignEntity campaignEntity = new CampaignEntity();
		try {
			campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
					userService.getUser(userInfo).getId(), 1, null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
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

	public CampaignEntity createOrUpdate(CampaignEntity campaignRequest, UserInfo userInfo, Long campaignId)
			throws OracleSQLException {
		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (campaignId == null) {
			campaignRequest.setUserId(userId);
			campaignRequest.setStatus(1);
			campaignRequest.setCreatedAt(new Date());
			campaignRequest.setExportFileType("png");
			logger.info("save : " + campaignRepository.save(campaignRequest));
			if (ObjectUtils.isNotEmpty(campaignRepository.save(campaignRequest))) {
				return campaignRepository.save(campaignRequest);
			} else {
				throw new OracleSQLException();
			}
		} else {
			CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
					userId, 1, null);
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
	}

	public String deleteCampaign(long campaignId, UserInfo userInfo) throws OracleSQLException {
		CampaignEntity campaignEntity = new CampaignEntity();
		try {
			campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
					userService.getUser(userInfo).getId(), 1, null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
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

	public CampaignEntity getCampaign(Long campaignId, UserInfo userInfo) {
		CampaignEntity campaignEntity = new CampaignEntity();
		try {
			campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
					userService.getUser(userInfo).getId(), 1, null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			return campaignEntity;
		} else {
			throw new NotFoundException("Campaign not exist");
		}
	}

	private Logger logger = Logger.getLogger(CampaignService.class.getName());
}
