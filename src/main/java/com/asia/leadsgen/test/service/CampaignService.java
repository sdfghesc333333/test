package com.asia.leadsgen.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import oracle.jdbc.driver.OracleSQLException;

@Service
public class CampaignService {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	UserService userService;

	public CampaignEntity create(CampaignEntity campaignEntity, UserInfo userInfo)
			throws OracleSQLException, LoginException {
		campaignEntity.setUserId(userService.getUser(userInfo).getId());
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
			throws OracleSQLException, LoginException {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null);
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

	public String deleteCampaign(long campaignId, UserInfo userInfo) throws OracleSQLException, LoginException {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null);
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

	public CampaignEntity getCampaign(Long campaignId, UserInfo userInfo) throws LoginException {
		CampaignEntity campaignEntity = campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null);
		if (ObjectUtils.isNotEmpty(campaignEntity)) {
			return campaignEntity;
		} else {
			throw new NotFoundException("Campaign not exist");
		}
	}

	public Page<CampaignEntity> list(int page, int pageSize, String startDateStr, String endDateStr, String search,
			String sort, String dir, UserInfo userInfo) throws LoginException, ParseException {
		Page<CampaignEntity> campaignEntity;
		Pageable pageable;

		Date startDate;
		Date endDate;
		if (StringUtils.isEmpty(startDateStr)) {
			startDate = formatDate("1609174800000");
		} else {
			startDate = formatDate(startDateStr);
		}

		if (StringUtils.isEmpty(endDateStr)) {
			endDate = new Date();
		} else {
			endDate = formatDate(endDateStr);
		}

		if (dir.equals("asc")) {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).ascending());
		} else {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(sort).descending());
		}

		if (StringUtils.isEmpty(search)) {
			campaignEntity = campaignRepository.findAllByUserIdAndDeletedAtAndCreatedAtBetween(pageable,
					userService.getUser(userInfo).getId(), null, startDate, endDate);
		} else {
			campaignEntity = campaignRepository.findAllByUserIdAndDeletedAtAndNameLikeAndCreatedAtBetween(pageable,
					userService.getUser(userInfo).getId(), null, "%"+search+"%", startDate, endDate);
		}

		return campaignEntity;
	}

	public Date formatDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\\'T\\'hh:mm:ss\\'Z\\'");
		Calendar affCal = Calendar.getInstance();
		affCal.setTimeInMillis(Long.valueOf(dateString));
		dateString = dateFormat.format(affCal.getTime());
		Date date = dateFormat.parse(dateString);
		return date;
	}

	private Logger logger = Logger.getLogger(CampaignService.class.getName());
}
