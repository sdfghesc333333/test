package com.asia.leadsgen.test.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.MockupEntity;

@Repository
public interface MockupRepository extends JpaRepository<MockupEntity, Long> {
	Page<MockupEntity> findAllByUserIdAndDeletedAt(Pageable pageable, Long userId, Date deletedAt);
}