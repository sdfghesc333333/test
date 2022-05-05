package com.asia.leadsgen.test.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MockupRequest {

	private String name;
	private String width;
	private String height;
	@JsonProperty(value = "file_path")
	private String filePath;
	private String printareas;
	private String conditions;
}
