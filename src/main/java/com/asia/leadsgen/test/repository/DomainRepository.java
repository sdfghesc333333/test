package com.asia.leadsgen.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.CampaignInfo;
import com.asia.leadsgen.test.model.DomainInfo;

@Repository
public interface DomainRepository extends JpaRepository<DomainInfo, String> {
	DomainInfo findDomainInfoById(String id);
}
