package com.asia.leadsgen.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.CampaignInfo;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignInfo, String> {
    CampaignInfo findCampaignInfoById(String id);
    
    List<CampaignInfo> findByTitle(String name);
    
    List<CampaignInfo> findByIdAndState(String campaignId, String state);
}
