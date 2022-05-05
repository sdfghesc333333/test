package com.asia.leadsgen.test.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CampaignRequest {

	@JsonProperty(value = "product_id")
	private String productId;
	private String handle;
	private String name;
	private String thumbnail;
	@JsonProperty(value = "fulfillment_id")
	private Long fulfillmentId;
	@JsonProperty(value = "product_type_id")
	private Long productTypeId;

}
