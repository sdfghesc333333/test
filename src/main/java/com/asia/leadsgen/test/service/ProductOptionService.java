package com.asia.leadsgen.test.service;

import java.util.Date;
import java.util.logging.Logger;

import javax.security.auth.login.LoginException;

import org.apache.commons.lang3.ObjectUtils;
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
import com.asia.leadsgen.test.util.DateTimeUtil;

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
			long campaignId) throws OracleSQLException {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

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

	public ProductOptionEntity updateOption(ProductOptionEntity productOptionEntity, UserInfo userInfo, long campaignId,
			long optionId) throws OracleSQLException {

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (ObjectUtils
				.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			if (ObjectUtils.isEmpty(productOptionRepository.findByIdAndUserIdAndCampaignIdAndDeletedAt(optionId, userId,
					campaignId, null))) {
				throw new NotFoundException("Option not exist");
			} else {
				ProductOptionEntity optionEntity = productOptionRepository
						.findByIdAndUserIdAndCampaignIdAndDeletedAt(optionId, userId, campaignId, null);
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
				e.printStackTrace();
			}
			index++;
		}
		return "msg : Success";
	}

	public String deleteOption(Long optionId, UserInfo userInfo, Long campaignId) {
		ProductOptionEntity productOptionEntity = null;
		try {
			productOptionEntity = productOptionRepository.findByIdAndUserIdAndCampaignIdAndDeletedAt(optionId,
					userService.getUser(userInfo).getId(), campaignId, null);
		} catch (LoginException e) {
			e.printStackTrace();
		}
		if (ObjectUtils.isEmpty(productOptionEntity)) {
			return "errors : Option not exist !";
		} else {
			productOptionEntity.setDeletedAt(new Date());
			productOptionRepository.save(productOptionEntity);
			return "msg : Success";
		}
	}

	public Page<ProductOptionEntity> list(Long campaignId, int page, int pageSize, String startDate, String endDate,
			String sort, String dir, UserInfo userInfo) {
		Pageable pageable;

		Long userId = null;
		try {
			userId = userService.getUser(userInfo).getId();
		} catch (LoginException e) {
			e.printStackTrace();
		}

		if (dir.equals("asc")) {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(com.asia.leadsgen.test.util.StringUtils.sortString(sort)).ascending());
		} else {
			pageable = PageRequest.of(page - 1, pageSize, Sort.by(com.asia.leadsgen.test.util.StringUtils.sortString(sort)).descending());
		}

		if (ObjectUtils
				.isEmpty(campaignRepository.findByIdAndUserIdAndStatusAndDeletedAt(campaignId, userId, 1, null))) {
			throw new NotFoundException("Campaign not exist");
		} else {
			Page<ProductOptionEntity> productOptionEntities = productOptionRepository
					.findAllByUserIdAndCampaignIdAndCreatedAtBetween(pageable, userId, campaignId,
							DateTimeUtil.startDateFomat(startDate), DateTimeUtil.endDateFomat(endDate));

			return productOptionEntities;
		}
	}

	private Logger logger = Logger.getLogger(ProductOptionService.class.getName());
}
