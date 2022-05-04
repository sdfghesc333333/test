package com.asia.leadsgen.test.model.request;

import javax.persistence.Column;

//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonRawValue;

import lombok.Data;

@Data
public class ClipartRequest {

	@Column(name = "name")
	private String name;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "type")
	private String type;

//	@Column(name = "clipart_categories", columnDefinition = "json")
//	@JsonProperty(value = "clipart_categories")
//	@JsonRawValue
//	private String clipartCategories;
}
