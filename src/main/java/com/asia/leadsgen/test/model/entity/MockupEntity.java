package com.asia.leadsgen.test.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mockups")
public class MockupEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	@JsonProperty(value = "user_id")
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "file_path")
	@JsonProperty(value = "file_path")
	private String filePath;

	@Column(name = "height")
	private String height;

	@Column(name = "width")
	private String width;

	@Column(name = "printareas", columnDefinition = "json")
	@JsonRawValue
	private String printareas;

	@Column(name = "conditions", columnDefinition = "json")
	@JsonRawValue
	private String conditions;

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
