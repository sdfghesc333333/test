package com.asia.leadsgen.fmerch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.fmerch.model.CampaignInfo;
import com.asia.leadsgen.fmerch.model.DomainInfo;

@Repository
public interface DomainRepository extends JpaRepository<DomainInfo, String> {
	DomainInfo findDomainInfoById(String id);
}
