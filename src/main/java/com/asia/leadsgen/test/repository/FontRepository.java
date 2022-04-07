package com.asia.leadsgen.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.CampaignEntity;
import com.asia.leadsgen.test.model.entity.FontEntity;

@Repository
public interface FontRepository extends JpaRepository<FontEntity, Long> {
	Page<CampaignEntity> findAllByUserId(Pageable pageable, Long userId);
	
	CampaignEntity findByIdAndUserIdAndStatus(Long id, Long userId, int status);
}
