package com.asia.leadsgen.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "campaigns")
@Data
public class CampaignEntity {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	@JsonProperty(value = "user_id")
	private Long userId;

	@Column(name = "product_id")
	@JsonProperty(value = "product_id")
	private String productId;

	@Column(name = "handle")
	private String handle;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	@JsonProperty(value = "status")
	private String status;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;
}
