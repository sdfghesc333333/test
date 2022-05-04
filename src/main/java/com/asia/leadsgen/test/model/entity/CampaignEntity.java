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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
//import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campaigns")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampaignEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(hidden = true)
//	@ApiModelProperty(hidden = true)
	private Long id;

//	@ApiModelProperty(
//		       required = false,
//		       hidden = true
//		    )
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

	@Column(name = "thumbnail")
	private String thumbnail;

	@Column(name = "design_url")
	@JsonProperty(value = "design_url")
	private String designUrl;

	@Column(name = "width")
	private String width;

	@Column(name = "height")
	private String height;

	@Column(name = "layers", columnDefinition = "json")
	@JsonRawValue
	private String layers;

	@Column(name = "fonts", columnDefinition = "json")
	@JsonRawValue
	private String fonts;

	@Column(name = "mockup_id")
	@JsonProperty(value = "mockup_id")
	private Long mockupId;

	@Column(name = "fulfillment_id")
	@JsonProperty(value = "fulfillment_id")
	private Long fulfillmentId;

	@Column(name = "product_type_id")
	@JsonProperty(value = "product_type_id")
	private Long productTypeId;

	@Column(name = "status")
	@JsonProperty(value = "status")
	private int status;

	@Column(name = "export_file_type")
	@JsonProperty(value = "export_file_type")
	private String exportFileType;

	@Column(name = "export_file_type_updated_at")
	@JsonProperty(value = "export_file_type_updated_at")
	private Date exportFileTypeUpdatedAt;

	@Column(name = "created_at")
	@JsonProperty(value = "created_at")
	private Date createdAt;

	@Column(name = "deleted_at")
	@JsonProperty(value = "deleted_at")
	private Date deletedAt;

	@Schema(hidden = true)
	@Column(name = "updated_at")
	@JsonProperty(value = "updated_at")
	private Date updatedAt;

//	public String getLayers() {
////		Gson g = new Gson();
////		return g.fromJson(layers);
//		return layers;
//	}
//
//	public void setLayers(String layers) {
//		this.layers = layers;
//	}

}
