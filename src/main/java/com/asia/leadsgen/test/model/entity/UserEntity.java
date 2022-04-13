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
@Data
@Table(name = "users")
public class UserEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "deleted_at")
	@JsonProperty(value = "deleted_at")
	private Date deletedAt;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;

	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;
	
	@Column(name = "aff_id")
	@JsonProperty(value = "aff_id")
	private String affId;
}
