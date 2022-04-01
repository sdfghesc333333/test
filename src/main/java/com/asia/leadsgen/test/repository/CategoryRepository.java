package com.asia.leadsgen.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.CategoryInfo;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryInfo, String> {
    CategoryInfo findCategoryInfoById(String id);
}
