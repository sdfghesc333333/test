package com.asia.leadsgen.test.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.CampaignEntity;

@Repository
public interface CampaignRepository extends JpaRepository<CampaignEntity, Long> {
	Page<CampaignEntity> findAllByUserIdAndDeletedAtAndCreatedAtBetween(Pageable pageable, Long userId, Date deletedAt,
			Date startDate, Date endDate);

	CampaignEntity findByIdAndUserIdAndStatusAndDeletedAt(Long id, Long userId, int status, Date deletedAt);

	Page<CampaignEntity> findAllByUserIdAndDeletedAtAndNameLikeAndCreatedAtBetween(Pageable pageable, Long userId, Date deletedAt,
			String name, Date startDate, Date endDate);
	
	@Query(value = "select count(*) from campaigns where created_at > (CURRENT_DATE - INTERVAL 30 DAY) and user_id = ? and deleted_at is null",nativeQuery = true)
    int countTotal30dayago(Long userId);
}
