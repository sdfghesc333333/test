package com.asia.leadsgen.test.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "TB_FMERCH_CAMPAIGN")
@Data
public class FmerchCampaignInfo {
	@Id
	@Column(name = "S_ID")
	private String id;

	@Column(name = "S_CAMPAIGN_ID")
	@JsonProperty(value = "campaign_id")
	private String campaignId;

	@Column(name = "S_CAMPAIGN_TITLE")
	@JsonProperty(value = "campaign_title")
	private String campaignTitle;

	@Column(name = "S_CAMPAIGN_URL")
	@JsonProperty(value = "campaign_url")
	private String campaignUrl;

	@Column(name = "S_NICHE_ID")
	@JsonProperty(value = "niche_id")
	private String nicheId;

	@Column(name = "S_NICHE_NAME")
	@JsonProperty(value = "niche_name")
	private String nicheName;

	@Column(name = "S_STATE")
	private String state;

	@Column(name = "D_CREATE")
	@JsonProperty(value = "create_at")
	private Date createAt;

	@Column(name = "S_PROFIT_RANGE")
	@JsonProperty(value = "profit_range")
	private String profitRange;

	@Column(name = "S_IMG_BACK_URL")
	@JsonProperty(value = "img_back_url")
	private String imgBackUrl;

	@Column(name = "S_IMG_FRONT_URL")
	@JsonProperty(value = "img_front_url")
	private String imgFrontUrl;

	@Column(name = "S_DOMAIN")
	@JsonProperty(value = "domain")
	private String domain;

	// Transient
	@Transient
	private List<ProductInfo> products;
	@Transient
	private String category;
	@Transient
	private String status;
	@Transient
	private String link;

	// Entity Relationships
//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "S_CAMPAIGN_ID", updatable = false, insertable = false, nullable = true)
//	private CampaignInfo campaignInfo;
}
