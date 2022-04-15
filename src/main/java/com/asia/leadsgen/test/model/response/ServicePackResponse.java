package com.asia.leadsgen.test.model.response;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class ServicePackResponse {

	@Id
	@Column(name = "id")
	private String id;

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
	private String isFullfeature;

	@Column(name = "is_enterprise")
	@JsonProperty(value = "is_enterprise")
	private String isEnterprise;

	@Column(name = "is_trial")
	@JsonProperty(value = "is_trial")
	private String isTrial;

	@Column(name = "deleted_at")
	@JsonProperty(value = "deleted_at")
	private String deletedAt;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private String createdAt;

	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private String updatedAt;

	@Transient
	private List<ServiceResponse> service;
	@Transient
	private String limitCampaign;
}
