package com.asia.leadsgen.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.UrlInfo;

@Repository
public interface UrlRepository extends JpaRepository<UrlInfo, String> {
	UrlInfo findUrlInfoByDomainIdAndRef(String domainId, String ref);
}
