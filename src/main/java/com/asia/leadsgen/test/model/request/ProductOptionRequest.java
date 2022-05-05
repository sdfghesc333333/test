package com.asia.leadsgen.test.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductOptionRequest {

	private String type;
	private String title;
	@JsonProperty(value = "clipart_category_id")
	private Long clipartCategoryId;
	@JsonProperty(value = "clipart_categories")
	private String clipartCategories;
	private String condition;
	private String settings;
	@JsonProperty(value = "bound_settings")
	private String boundSettings;
	@JsonProperty(value = "clipart_category_type")
	private String clipartCategotyType;
	@JsonProperty(value = "condition_type")
	private String conditionType;
	private int position;
}
