package com.asia.leadsgen.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.NicheInfo;

@Repository
public interface NicheRepository extends JpaRepository<NicheInfo, String>{
	boolean existsByName(String name);
	
	Page<NicheInfo> findByNameAndState(String name, String state, Pageable pageable);
	
	Page<NicheInfo> findByState(String state, Pageable pageable);	
}

