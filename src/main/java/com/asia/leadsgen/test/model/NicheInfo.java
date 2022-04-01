package com.asia.leadsgen.test.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_FMERCH_NICHE")
public class NicheInfo {
	@Id
	@Column(name = "S_ID")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String id;
	
	@Column(name = "S_NAME", unique = true)
	private String name;

	@Column(name = "S_STATE", columnDefinition = "approved")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String state;
	
	@Column(name = "N_TOTAL_CAMPAIGN")
	@JsonProperty(value = "n_total_campaign")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private int totalCampaign;

	@Column(name = "S_TYPE")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private String type;

	@Column(name = "D_CREATE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty(value = "create_at")
	@Schema(accessMode = Schema.AccessMode.READ_ONLY)
	private Date create;
}
