package com.asia.leadsgen.test.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.ClipartEntity;

@Repository
public interface ClipartRepository extends JpaRepository<ClipartEntity, Long> {
//	Page<ClipartEntity> findAllByUserIdAndDeletedAtAndClipartCategories(Pageable pageable, Long userId,
//			Date deletedDate, String clipartCategories);

	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null", nativeQuery = true)
	List<ClipartEntity> list(Pageable pageable, Long userId);
}