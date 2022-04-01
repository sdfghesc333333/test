package com.asia.leadsgen.fmerch.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asia.leadsgen.fmerch.exception.NotFoundException;
import com.asia.leadsgen.fmerch.exception.OracleException;
import com.asia.leadsgen.fmerch.model.CampaignInfo;
import com.asia.leadsgen.fmerch.model.FmerchCampaignInfo;
import com.asia.leadsgen.fmerch.model.OfferInfo;
import com.asia.leadsgen.fmerch.model.ResultObjectInfo;
import com.asia.leadsgen.fmerch.model.UserInfo;
import com.asia.leadsgen.fmerch.model.response.FmerchCampaignResponse;
import com.asia.leadsgen.fmerch.repository.CampaignRepository;
import com.asia.leadsgen.fmerch.repository.DomainRepository;
import com.asia.leadsgen.fmerch.repository.FmerchCampaignRepository;
import com.asia.leadsgen.fmerch.repository.OfferRepository;
import com.asia.leadsgen.fmerch.repository.UrlRepository;
import com.asia.leadsgen.fmerch.repository.impl.FmerchCampaignRepositoryImpl;
import com.asia.leadsgen.fmerch.util.GenerateStringUtils;

@Service
public class OfferService {
	@Autowired
	FmerchCampaignRepositoryImpl fmerchCampaignRepositoryImpl;

	@Autowired
	OfferRepository offerRepository;

	@Autowired
	FmerchCampaignRepository fmerchCampaignRepository;

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	DomainRepository domainRepository;

	@Autowired
	UrlRepository urlRepository;

	@Autowired
	GenerateStringUtils generateStringUtils;

	@Autowired
	CampaignService campaignService;

	public FmerchCampaignInfo getOfferById(String fmerchCampaignId, String userId) {

		FmerchCampaignInfo fmerchCampaignInfo = campaignService.getFmerchCampById(fmerchCampaignId);

		if (fmerchCampaignInfo != null) {
			String state = fmerchCampaignInfo.getState();
			String campId = fmerchCampaignInfo.getCampaignId();
			String status = statusOffer(campId, state, userId);
			fmerchCampaignInfo.setStatus(status);

			return fmerchCampaignInfo;
		} else {
			throw new NotFoundException("Offer Not Found");
		}
	}

	@Transactional
	public OfferInfo createOffer(UserInfo userInfo, OfferInfo offerInfo) {
		Date date = new Date();

		OfferInfo result = new OfferInfo();
		String id = generateStringUtils.generateId();
		String userId = userInfo.getUserId();
		String campaignId = offerInfo.getCampaignId();
		String state = "approved";
		result.setId(id);
		result.setUserId(userId);
		result.setCampaignId(campaignId);
		result.setState(state);
		result.setCreateAt(date);
		if (offerRepository.existsByCampaignIdAndUserId(campaignId, userId)) {
			throw new OracleException("offer already exist!");
		} else {
			return offerRepository.save(result);
		}
	}

	public String statusOffer(String campaignId, String fmerchCampaignState, String userId) {

		List<CampaignInfo> campaignInfo = campaignRepository.findByIdAndState(campaignId, "lauching");

		if (offerRepository.existsByCampaignIdAndUserId(campaignId, userId)) {
			if (campaignInfo.isEmpty() & fmerchCampaignState.equals("approved")) {
				return "active";
			} else {
				return "inactive";
			}

		} else {
			return "new";
		}

	}

//	=====================================================================================================================================

	public ResultObjectInfo<FmerchCampaignResponse> listOfferv2(String userId, int page, int size, String status,
			String startDate, String endDate, String search, String sort, String dir) throws ParseException {

		// Date
		Date startDate1;
		Date endDate1;
		if (StringUtils.isEmpty(startDate)) {
			startDate1 = formatDate("1609174800000");
		} else {
			startDate1 = formatDate(startDate);
		}

		if (StringUtils.isEmpty(endDate)) {
			endDate1 = new Date();
		} else {
			endDate1 = formatDate(endDate);
		}

		ResultObjectInfo<FmerchCampaignResponse> rs = fmerchCampaignRepositoryImpl.listOffers(userId, startDate1,
				endDate1, status, sort, dir, page, size, search);
		return rs;

	}

	public Date formatDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd\\'T\\'hh:mm:ss\\'Z\\'");
		Calendar affCal = Calendar.getInstance();
		affCal.setTimeInMillis(Long.valueOf(dateString));
		dateString = dateFormat.format(affCal.getTime());
		Date date = dateFormat.parse(dateString);
		return date;
	}

	private Logger logger = Logger.getLogger(OfferService.class.getName());
}
