package com.asia.leadsgen.test.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "relation_pack_service")
@Data
public class ServicePackRelation {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "service_id")
	@JsonProperty(value = "service_id")
	private String serviceId;

	@Column(name = "pack_service_id")
	@JsonProperty(value = "pack_service_id")
	private String packServiceId;

	@Column(name = "limit_campaign")
	@JsonProperty(value = "limit_campaign")
	private String limitCampaign;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;

}
