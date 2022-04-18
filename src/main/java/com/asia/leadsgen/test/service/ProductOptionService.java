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
import com.asia.leadsgen.test.model.SortOptionRequestModel;
import com.asia.leadsgen.test.model.UserInfo;
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

	@Autowired
	UserService userService;

	public ProductOptionEntity createProductOption(ProductOptionEntity productOptionEntity, UserInfo userInfo,
			long campaignId) throws OracleSQLException, LoginException {

		if (ObjectUtils.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			productOptionEntity.setUserId(userService.getUser(userInfo).getId());
			productOptionEntity.setCampaignId(campaignId);
			productOptionEntity.setCreatedAt(new Date());
			if (ObjectUtils.isNotEmpty(productOptionRepository.save(productOptionEntity))) {
				return productOptionRepository.save(productOptionEntity);
			} else {
				throw new OracleSQLException();
			}
		}
	}

	public ProductOptionEntity updateOption(ProductOptionEntity productOptionEntity, UserInfo userInfo, long campaignId,
			long optionId) throws OracleSQLException, LoginException {

		if (ObjectUtils.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			if (ObjectUtils.isEmpty(productOptionRepository.findByIdAndUserIdAndCampaignIdAndDeletedAt(optionId,
					userService.getUser(userInfo).getId(), campaignId, null))) {
				throw new NotFoundException("Option not exist");
			} else {
				ProductOptionEntity optionEntity = productOptionRepository.findByIdAndUserIdAndCampaignIdAndDeletedAt(
						optionId, userService.getUser(userInfo).getId(), campaignId, null);
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
	}

	public String sortOptions(SortOptionRequestModel optionIds, UserInfo userInfo, Long campaignId) {
		if (optionIds.getOptionIds().isEmpty()) {
			return "errors : Empty ids";
		}

		int index = 0;
		for (Long id : optionIds.getOptionIds()) {
			try {
				ProductOptionEntity productOptionEntity = productOptionRepository
						.findByIdAndUserIdAndCampaignIdAndDeletedAt(id, userService.getUser(userInfo).getId(),
								campaignId, null);
				if (ObjectUtils.isNotEmpty(productOptionEntity)) {
					productOptionEntity.setPosition(index);
					productOptionRepository.save(productOptionEntity);
				}
			} catch (LoginException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			index++;
		}
		return "msg : Success";
	}

	public String deleteOption(Long optionId, UserInfo userInfo, Long campaignId) throws LoginException {
		ProductOptionEntity productOptionEntity = productOptionRepository.findByIdAndUserIdAndCampaignIdAndDeletedAt(
				optionId, userService.getUser(userInfo).getId(), campaignId, null);
		if (ObjectUtils.isEmpty(productOptionEntity)) {
			return "errors : Option not exist !";
		} else {
			productOptionEntity.setDeletedAt(new Date());
			productOptionRepository.save(productOptionEntity);
			return "msg : Success";
		}
	}

	public Page<ProductOptionEntity> list(Long campaignId, int page, int pageSize, String startDateStr,
			String endDateStr, String sort, String dir, UserInfo userInfo) throws ParseException, LoginException {
		Page<ProductOptionEntity> productOptionEntities;
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

		if (ObjectUtils.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId,
				userService.getUser(userInfo).getId(), 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			productOptionEntities = productOptionRepository.findAllByUserIdAndCampaignIdAndCreatedAtBetween(pageable,
					userService.getUser(userInfo).getId(), campaignId, startDate, endDate);

			return productOptionEntities;
		}
	}

	public Date formatDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\\'T\\'hh:mm:ss\\'Z\\'");
		Calendar affCal = Calendar.getInstance();
		affCal.setTimeInMillis(Long.valueOf(dateString));
		dateString = dateFormat.format(affCal.getTime());
		Date date = dateFormat.parse(dateString);
		return date;
	}

	private Logger logger = Logger.getLogger(ProductOptionService.class.getName());
}
