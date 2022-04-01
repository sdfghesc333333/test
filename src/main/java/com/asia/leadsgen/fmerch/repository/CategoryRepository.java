package com.asia.leadsgen.fmerch.repository;

import com.asia.leadsgen.fmerch.model.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryInfo, String> {
    CategoryInfo findCategoryInfoById(String id);
}
