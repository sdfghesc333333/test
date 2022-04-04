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
@Data
@Table(name = "clipart_templates")
public class ClipartEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	@JsonProperty(value = "user_id")
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "type")
	private String type;

	@Column(name = "clipart_categories")
	@JsonProperty(value = "clipart_categories")
	private String clipartCategories;

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
