package com.asia.leadsgen.fmerch.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "TB_CAMPAIGN")
@Data
public class CampaignInfo {
	@Id
	@Column(name = "S_ID")
	private String id;

	@Column(name = "S_CATEGORY_IDS")
	@JsonProperty(value = "category_id")
	private String categoryId;

	@Column(name = "S_DOMAIN_ID")
	@JsonProperty(value = "domain_id")
	private String domainId;

	@Column(name = "S_TITLE")
	private String title;

	@Column(name = "S_STATE")
	@JsonProperty(value = "state")
	private String state;

	// Entity Relationships
//	@OneToOne(mappedBy = "campaignInfo", fetch = FetchType.LAZY)
//	private FmerchCampaignInfo fmerchCampaignInfo;
//
//	@OneToMany(mappedBy = "campaignInfo", fetch = FetchType.LAZY)
//	private List<OfferInfo> offerInfo;

}
