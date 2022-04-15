package com.asia.leadsgen.test.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.response.ServicePackResponse;
import com.asia.leadsgen.test.model.response.ServiceResponse;

@Repository
public class ServicePackRepositoryImpl {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<ServicePackResponse> list() {
//		String sql = "SELECT t1.*, t3.* FROM pack_services t1 INNER JOIN relation_pack_service t2 ON t2.pack_service_id = t1.id INNER JOIN service t3 ON t3.id = t2.service_id";

		StringBuilder sql = new StringBuilder(
				"SELECT t1.id as pack_service_id, t1.title, t1.description as pack_service_desc, t1.monthly_fee, t1.extra_fee, t1.setting_display, t1.is_fullfeature, t1.is_enterprise, t1.is_trial, t1.deleted_at, t1.created_at as pack_service_craeted_at, t1.updated_at as pack_service_updated_at, \r\n"
						+ "t3.id as service_id, t3.name, t3.description as service_desc, t3.role, t3.key_name, t3.created_at as service_craeted_at , t3.updated_at as service_updated_at FROM pack_services t1\r\n"
						+ "INNER JOIN relation_pack_service t2 ON t2.pack_service_id = t1.id\r\n"
						+ "INNER JOIN service t3 ON t3.id = t2.service_id");
		sql.append(" where t1.is_trial = 0");

		Query query = entityManager.createNativeQuery(sql.toString());

		return formatServicePackResponse(query.getResultList());
	}

//	public List<ServicePackResponse> formatServicePackResponse(List<Object[]> rows) {
//		List<ServicePackResponse> servicePackResponses = new ArrayList<>();
//
//		rows.forEach(row -> {
//			String id = String.valueOf(row[0]);
//			System.out.println(id);
//			ServicePackResponse servicePackResponse = new ServicePackResponse();
//			ServiceResponse serviceResponse = formatServiceResponse(row);
//
//			List<ServiceResponse> serviceRes = new ArrayList<ServiceResponse>();
//
//			if (servicePackResponse.getId() == id && serviceResponse.getId() != String.valueOf(row[12])) {
////				if (serviceResponse.)
//				serviceRes.add(serviceResponse);
////				tempMap.get(id).getService().add((ServiceResponse) serviceResponse);
//			} else {
////				ServicePackResponse servicePackResponse = new ServicePackResponse();
//				servicePackResponse.setId(id);
//				servicePackResponse.setTitle(String.valueOf(row[1]));
//				servicePackResponse.setDescription(String.valueOf(row[2]));
//				servicePackResponse.setMonthlyFee(String.valueOf(row[3]));
//				servicePackResponse.setExtraFee(String.valueOf(row[4]));
//				servicePackResponse.setSettingDisplay(String.valueOf(row[5]));
//				if (String.valueOf(row[6]).equals("1")) {
//					servicePackResponse.setFullfeature(true);
//				} else {
//					servicePackResponse.setFullfeature(false);
//				}
//				if (String.valueOf(row[7]).equals("1")) {
//					servicePackResponse.setEnterprise(true);
//				} else {
//					servicePackResponse.setEnterprise(false);
//				}
//				if (String.valueOf(row[8]).equals("1")) {
//					servicePackResponse.setTrial(true);
//				} else {
//					servicePackResponse.setTrial(false);
//				}
//				servicePackResponse.setDeletedAt(String.valueOf(row[9]));
//				servicePackResponse.setCreatedAt(String.valueOf(row[10]));
//				servicePackResponse.setUpdatedAt(String.valueOf(row[11]));
////				servicePackResponse.getService().add(formatServiceResponse(row));
//				serviceRes.add(formatServiceResponse(row));
////				tempMap.put(id, servicePackResponse);
////				servicePackResponses.add(tempMap.get(id));
//			}
//			servicePackResponse.setService(serviceRes);
//			System.out.println(servicePackResponse);
//			servicePackResponses.add(servicePackResponse);
//		});
//
//		return servicePackResponses;
//	}

	public List<ServicePackResponse> formatServicePackResponse(List<Object[]> rows) {

		List<ServicePackResponse> servicePackResponses = new ArrayList<>();

		Set<String> ids = new HashSet<String>();
		for (Object[] row : rows) {
			ids.add(String.valueOf(row[0]));
		}

		for (String id : ids) {
			ServicePackResponse servicePackResponse = new ServicePackResponse();
			List<ServiceResponse> serviceRes = new ArrayList<ServiceResponse>();
			rows.forEach(row -> {
				ServiceResponse serviceResponse = new ServiceResponse();
				if (String.valueOf(row[0]).equals(id)) {
					servicePackResponse.setId(id);
					servicePackResponse.setTitle(String.valueOf(row[1]));
					servicePackResponse.setDescription(String.valueOf(row[2]));
					servicePackResponse.setMonthlyFee(String.valueOf(row[3]));
					servicePackResponse.setExtraFee(String.valueOf(row[4]));
					servicePackResponse.setSettingDisplay(String.valueOf(row[5]));
					servicePackResponse.setIsFullfeature(String.valueOf(row[6]));
					servicePackResponse.setIsEnterprise(String.valueOf(row[7]));
					servicePackResponse.setIsTrial(String.valueOf(row[8]));
					servicePackResponse.setDeletedAt(String.valueOf(row[9]));
					servicePackResponse.setCreatedAt(String.valueOf(row[10]));
					servicePackResponse.setUpdatedAt(String.valueOf(row[11]));
					serviceResponse = formatServiceResponse(row);
					serviceRes.add(serviceResponse);
				}
			});
			servicePackResponse.setService(serviceRes);
			servicePackResponses.add(servicePackResponse);
		}

		return servicePackResponses;
	}

	public ServiceResponse formatServiceResponse(Object[] row) {
		ServiceResponse serviceResponse = new ServiceResponse();

		serviceResponse.setId(String.valueOf(row[12]));
		serviceResponse.setName(String.valueOf(row[13]));
		serviceResponse.setDescription(String.valueOf(row[14]));
		serviceResponse.setRole(String.valueOf(row[15]));
		serviceResponse.setKeyName(String.valueOf(row[16]));
		serviceResponse.setCreatedAt(String.valueOf(row[17]));
		serviceResponse.setUpdatedAt(String.valueOf(row[18]));

		return serviceResponse;
	}
}
