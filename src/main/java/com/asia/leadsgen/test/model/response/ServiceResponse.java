package com.asia.leadsgen.test.model.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class ServiceResponse {

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "role")
	private String role;
	
	@Column(name = "key_name")
	@JsonProperty(value = "key_name")
	private String keyName;
	
	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private String createdAt;
	
	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private String updatedAt;

}
