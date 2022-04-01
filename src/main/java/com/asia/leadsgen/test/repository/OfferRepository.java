package com.asia.leadsgen.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.OfferInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<OfferInfo, String> {
	OfferInfo findOfferInfoById(String id);

	List<OfferInfo> findByCampaignIdAndUserId(String campaignId, String userId);

	boolean existsByCampaignIdAndUserId(String campaignId, String userId);

	boolean existsByCampaignId(String campaignId);

	List<OfferInfo> findByCampaignIdAndState(String campaignId, String state);
	
	OfferInfo findByCampaignId(String campaignId);

}
