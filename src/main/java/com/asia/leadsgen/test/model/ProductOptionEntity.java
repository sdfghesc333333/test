package com.asia.leadsgen.test.model;

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
@Table(name = "product_options")
@Data
public class ProductOptionEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	@JsonProperty(value = "user_id")
	private Long userId;

	@Column(name = "campaign_id")
	@JsonProperty(value = "campaign_id")
	private Long campaignId;

	@Column(name = "type")
	private String type;

	@Column(name = "title")
	private String title;

	@Column(name = "clipart_category_id")
	@JsonProperty(value = "clipart_category_id")
	private Long clipartCategoryId;

	@Column(name = "clipart_categories")
	@JsonProperty(value = "clipart_categories")
	private String clipartCategories;

	@Column(name = "conditions")
	private String conditions;

	@Column(name = "settings")
	private String settings;

	@Column(name = "bound_settings")
	@JsonProperty(value = "bound_settings")
	private String boundSettings;

	@Column(name = "clipart_category_type")
	@JsonProperty(value = "clipart_category_type")
	private String clipartCategoryType;

	@Column(name = "condition_type")
	@JsonProperty(value = "condition_type")
	private String conditionType;

	@Column(name = "position")
	private String position;

	@Column(name = "deleted_at")
	@JsonProperty(value = "deleted_at")
	private Date deletedAt;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;

}
