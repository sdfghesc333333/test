package com.asia.leadsgen.test.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.ClipartEntity;
import com.asia.leadsgen.test.model.response.ShowStatusResponse;

@Repository
public interface ClipartRepository extends JpaRepository<ClipartEntity, Long> {

	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null and id = ?", nativeQuery = true)
	ClipartEntity getById(Long userId, Long catId);

//	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null and created_at between ? and ?", nativeQuery = true)
//	List<ClipartEntity> list(Pageable pageable, Long userId, Date startDate, Date endDate);
//
//	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null and name like ? and created_at between ? and ?", nativeQuery = true)
//	List<ClipartEntity> listLikeName(Pageable pageable, Long userId, String name, Date startDate, Date endDate);

	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null ", nativeQuery = true)
	List<ClipartEntity> list(Pageable pageable, Long userId);

	@Query(value = "select * from clipart_templates where user_id = ? and deleted_at is null and clipart_categories is not null and name like ?", nativeQuery = true)
	List<ClipartEntity> listLikeName(Pageable pageable, Long userId, String search);

	@Query(value = "show table status like 'clipart_templates'", nativeQuery = true)
	ShowStatusResponse show();
}