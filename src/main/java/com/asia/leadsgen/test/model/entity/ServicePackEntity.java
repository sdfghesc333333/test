package com.asia.leadsgen.test.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "pack_services")
@Data
public class ServicePackEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "monthly_fee")
	@JsonProperty(value = "monthly_fee")
	private String monthlyFee;

	@Column(name = "extra_fee")
	@JsonProperty(value = "extra_fee")
	private String extraFee;

	@Column(name = "setting_display")
	@JsonProperty(value = "setting_display")
	private String settingDisplay;

	@Column(name = "is_fullfeature")
	@JsonProperty(value = "is_fullfeature")
	private boolean isFullfeature;

	@Column(name = "is_enterprise")
	@JsonProperty(value = "is_enterprise")
	private boolean isEnterprise;

	@Column(name = "is_trial")
	@JsonProperty(value = "is_trial")
	private boolean isTrial;

	@Column(name = "deleted_at")
	@JsonProperty(value = "deleted_at")
	private Date deletedAt;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;

	@Transient
	private List<ServiceEntity> service;
	@Transient
	private String limitCampaign;

}
