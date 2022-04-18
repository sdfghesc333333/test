package com.asia.leadsgen.test.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.ProductOptionEntity;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOptionEntity, Long> {
	Page<ProductOptionEntity> findAllByUserIdAndCampaignId(Pageable pageable, Long userId, Long campaignId);

//	CampaignEntity findByIdAndUserIdAndStatus(Long id, Long userId, int status);

	ProductOptionEntity findByIdAndUserIdAndCampaignIdAndDeletedAt(Long id, Long userId, Long campaignId,
			Date deletedAt);

	Page<ProductOptionEntity> findAllByUserIdAndCampaignIdAndCreatedAtBetween(Pageable pageable, Long userId,
			Long campaignId, Date start, Date end);
}