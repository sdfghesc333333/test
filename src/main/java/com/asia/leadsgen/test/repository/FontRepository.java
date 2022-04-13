package com.asia.leadsgen.test.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.FontEntity;

@Repository
public interface FontRepository extends JpaRepository<FontEntity, Long> {
	Page<FontEntity> findAllByUserIdAndDeletedAt(Pageable pageable, Long userId, Date deletedAt);
}
