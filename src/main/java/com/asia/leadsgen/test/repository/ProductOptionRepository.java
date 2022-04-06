package com.asia.leadsgen.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.ProductOptionEntity;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, Long> {
	Page<ProductOptionEntity> findAllByUserIdAndCampaignId(Pageable pageable, Long userId, Long campaignId);
	
//	CampaignEntity findByIdAndUserIdAndStatus(Long id, Long userId, int status);
	
	ProductOptionEntity findByIdAndUserIdAndCampaignId(Long id, Long userId, Long campaignId);
}