package com.asia.leadsgen.test.model.request;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CampaignRequest {
	@Column(name = "product_id")
	@JsonProperty(value = "product_id")
	private String productId;

	@Column(name = "handle")
	private String handle;

	@Column(name = "name")
	private String name;

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "fulfillment_id")
	@JsonProperty(value = "fulfillment_id")
	private Long fulfillmentId;

	@Column(name = "product_type_id")
	@JsonProperty(value = "product_type_id")
	private Long productTypeId;
}
