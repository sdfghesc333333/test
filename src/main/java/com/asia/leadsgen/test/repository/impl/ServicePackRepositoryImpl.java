package com.asia.leadsgen.test.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.entity.ServicePackEntity;

@Repository
public class ServicePackRepositoryImpl {

	@Autowired
	EntityManager entityManager;

	public List<ServicePackEntity> list() {
		String sql = "SELECT * FROM pack_services ps INNER JOIN relation_pack_service rps on rps.pack_service_id = ps.id INNER JOIN service s on s.id = rps.service_id";

		return null;
	}
}
