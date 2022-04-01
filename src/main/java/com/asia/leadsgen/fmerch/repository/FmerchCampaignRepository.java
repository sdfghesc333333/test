package com.asia.leadsgen.fmerch.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.fmerch.model.FmerchCampaignInfo;

@Repository
public interface FmerchCampaignRepository extends JpaRepository<FmerchCampaignInfo, String> {

	FmerchCampaignInfo findFmerchCampaignInfoByCampaignId(String campaignId);

	FmerchCampaignInfo findFmerchCampaignInfoById(String id);
//
//	FmerchCampaignInfo findByCampaignId(String campaignId);
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE tb.createAt BETWEEN :start AND :end order by cast(regexp_substr(s_profit_range, '[^-$]+') as float) ASC")
//	List<FmerchCampaignInfo> findAllFmerchCampaignASC(@Param("start") Date startDate, @Param("end") Date endDate);
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE tb.createAt BETWEEN :start AND :end order by cast(regexp_substr(s_profit_range, '[^-$]+') as float) DESC")
//	List<FmerchCampaignInfo> findAllFmerchCampaignDESC(@Param("start") Date startDate, @Param("end") Date endDate);
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE (tb.createAt BETWEEN :start AND :end) AND (tb.campaignId = :id) order by cast(regexp_substr(s_profit_range, '[^-$]+') as float) DESC")
//	List<FmerchCampaignInfo> findByCampaignDESC(@Param("id") String campaignId, @Param("start") Date startDate,
//			@Param("end") Date endDate);
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE (tb.createAt BETWEEN :start AND :end) AND (tb.campaignId = :id) order by cast(regexp_substr(s_profit_range, '[^-$]+') as float) ASC")
//	List<FmerchCampaignInfo> findByCampaignASC(@Param("id") String campaignId, @Param("start") Date startDate,
//			@Param("end") Date endDate);
//
////
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE tb.createAt BETWEEN :start AND :end")
//	List<FmerchCampaignInfo> findAllFmerchCampaign(@Param("start") Date startDate, @Param("end") Date endDate,
//			Sort sort);
//
//	@Query("select tb from FmerchCampaignInfo tb WHERE (tb.createAt BETWEEN :start AND :end) AND (tb.campaignId = :id)")
//	List<FmerchCampaignInfo> findByCampaign(@Param("id") String campaignId, @Param("start") Date startDate,
//			@Param("end") Date endDate, Sort sort);
//
//	/*
//	 * V2 list offers
//	 */
//
//	List<FmerchCampaignInfo> findAllByCreateAtBetween(Date startDate, Date endDate, Sort sort);
//
//	List<FmerchCampaignInfo> findAllByCreateAtBetween(Date startDate, Date endDate);
//
////	===================================================================================================================
//	
//	List<FmerchCampaignInfo> findByCreateAtBetweenAndState(Pageable pageable, Date startDate, Date endDate, String state);
//	
//	List<FmerchCampaignInfo> findByCreateAtBetween(Pageable pageable, Date startDate, Date endDate);
//	
//	List<FmerchCampaignInfo> findByCreateAtBetweenAndStateAndCampaignIdOrCampaignTitleLike(Pageable pageable, Date startDate, Date endDate, String state, String campaignId, String campaignTitle);
//	
//	List<FmerchCampaignInfo> findByCreateAtBetweenAndCampaignIdOrCampaignTitleLike(Pageable pageable, Date startDate, Date endDate, String campaignId, String campaignTitle);
}
