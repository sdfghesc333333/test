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
@Table(name = "campaigns")
@Data
public class FontEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	@JsonProperty(value = "user_id")
	private Long userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "path")
	private String path;
	
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
