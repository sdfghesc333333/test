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
@Table(name = "service")
@Data
public class ServiceEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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
	private Date createdAt;
	
	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;

}
