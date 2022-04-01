package com.asia.leadsgen.test.model.response;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
public class FmerchCampaignResponse {
	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "camp_id")
	@JsonProperty(value = "campaign_id")
	private String campaignId;

	@Column(name = "title")
	@JsonProperty(value = "campaign_title")
	private String campaignTitle;

	@Column(name = "url")
	@JsonProperty(value = "campaign_url")
	private String campaignUrl;

	@Column(name = "create_at")
	@JsonProperty(value = "create_at")
	private Date createAt;

	@Column(name = "domain")
	@JsonProperty(value = "domain")
	private String domain;

	@Column(name = "back")
	@JsonProperty(value = "img_back_url")
	private String imgBackUrl;

	@Column(name = "front")
	@JsonProperty(value = "img_front_url")
	private String imgFrontUrl;

	@Column(name = "niche_id")
	@JsonProperty(value = "niche_id")
	private String nicheId;

	@Column(name = "niche_name")
	@JsonProperty(value = "niche_name")
	private String nicheName;

	@Column(name = "profit_range")
	@JsonProperty(value = "profit_range")
	private String profitRange;

	@Column(name = "state")
	private String state;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "link")
	private String link;
}
