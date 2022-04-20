package com.asia.leadsgen.test.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.ClipartEntityResponse;
import com.asia.leadsgen.test.model.response.ShowStatusResponse;

@Repository
public interface ClipartRepository extends JpaRepository<ClipartEntityResponse, Long> {
//	Page<ClipartEntity> findAllByUserIdAndDeletedAtAndClipartCategories(Pageable pageable, Long userId,
//			Date deletedDate, String clipartCategories);

	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null", nativeQuery = true)
	List<ClipartEntityResponse> list(Pageable pageable, Long userId, Date startDate, Date endDate);
	
	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null and id = ?", nativeQuery = true)
	ClipartEntityResponse getById(Long userId, Long catId);

	@Query(value = "show table status like 'clipart_templates'", nativeQuery = true)
	ShowStatusResponse show();
}