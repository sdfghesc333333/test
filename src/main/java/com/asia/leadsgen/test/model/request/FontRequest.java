package com.asia.leadsgen.test.model.request;

import javax.persistence.Column;

import lombok.Data;

@Data
public class FontRequest {
	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
}
