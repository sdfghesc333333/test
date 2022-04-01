package com.asia.leadsgen.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;

@Entity
@Table(name = "TB_FMERCH_OFFER")
@Data
public class OfferInfo {
	@Id
	@Column(name = "S_ID")
	@Hidden
	private String id;

	@Column(name = "S_USER_ID")
	@JsonProperty(value = "user_id")
	private String userId;

	@Column(name = "S_CAMPAIGN_ID")
	@JsonProperty(value = "campaign_id")
	private String campaignId;

	@Column(name = "S_STATE")
	private String state;

	@Column(name = "D_CREATE")
	@JsonProperty(value = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;

	@Column(name = "D_UPDATE")
	@JsonProperty(value = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@Transient // đánh dấu một thuộc tính trong entity class không phải là một cột tương ứng
				// trong database
	@JsonProperty(value = "campaign")
	private FmerchCampaignInfo fmerchCampaignInfo;
	@Transient
	private String category;
	@Transient
	@JsonProperty(value = "conversion_type")
	private String conversionType;
	@Transient
	private String countries;
	@Transient
	@JsonProperty(value = "conversion_window")
	private String conversionWindow;

	// Entity Relationships
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "S_CAMPAIGN_ID", updatable = false, insertable = false, nullable = true)
//	private CampaignInfo campaignInfo;

}