package com.asia.leadsgen.test.repository.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.ResultObjectInfo;
import com.asia.leadsgen.test.model.response.FmerchCampaignResponse;

@Repository
public class FmerchCampaignRepositoryImpl {

	@Autowired
	EntityManager entityManager;

	public ResultObjectInfo<FmerchCampaignResponse> listOffers(String userId, Date startDate, Date endDate,
			String state, String sort, String dir, int page, int pageSize, String search) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(
				"SELECT tb1.s_id AS id, tb1.s_campaign_id AS camp_id, tb1.s_campaign_title AS title, tb1.s_campaign_url AS url, tb1.d_create AS create_at, tb1.s_domain AS domain,"
						+ " tb1.s_img_back_url AS back, tb1.s_img_front_url AS front, tb1.s_niche_id AS niche_id, tb1.s_niche_name AS niche_name, tb1.s_profit_range AS profit_range,"
						+ " tb1.s_state AS state, nvl(tb2.s_state, 'new') AS status,'https://'||tb1.s_domain||'/shop/'||tb1.s_campaign_url as link FROM tb_fmerch_campaign tb1"
						+ " LEFT OUTER JOIN tb_fmerch_offer    tb2 ON ( tb1.s_campaign_id = tb2.s_campaign_id AND tb2.s_user_id = :userId ) "
//						+ " INNER JOIN tb_campaign        tb3 ON tb3.s_id = tb1.s_campaign_id "
						+ " WHERE tb1.d_create BETWEEN :startDate AND :endDate"
//						+ " AND tb1.s_state = 'approved' AND tb3.s_state = 'launching'"
						+ " AND ( '" + state + "' IS NULL OR nvl(tb2.s_state, 'new') = '" + state + "' )");

		if (StringUtils.isNotEmpty(search)) {
			queryStr.append(
					" and (tb1.s_campaign_id = '" + search + "' or tb1.s_campaign_title like '%" + search + "%' )");
		}

		if(sort.equals("payout")) {
			sort = " CAST(regexp_substr(s_profit_range, '[^-$]+') AS FLOAT)";
		}

		queryStr.append(" ORDER BY " + sort + " " + dir + "");

		Query query = entityManager.createNativeQuery(queryStr.toString(), FmerchCampaignResponse.class)
				.setParameter("userId", userId).setParameter("startDate", startDate).setParameter("endDate", endDate);

		int total = query.getResultList().size();

		query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);

		return new ResultObjectInfo<FmerchCampaignResponse>(query.getResultList(), page, total);

	}

	private Logger logger = Logger.getLogger(FmerchCampaignRepositoryImpl.class.getName());
}
