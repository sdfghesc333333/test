package com.asia.leadsgen.test.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.CampaignEntity;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
	Page<CampaignEntity> findAllByUserIdAndDeletedAt(Pageable pageable, Long userId, Date deletedAt);
	
	CampaignEntity findByIdAndUserIdAndStatusAndDeletedAt(Long id, Long userId, int status, Date deletedAt);
}
