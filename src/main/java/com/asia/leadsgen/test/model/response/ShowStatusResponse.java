package com.asia.leadsgen.test.model.response;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ShowStatusResponse {

	@Column(name = "name")
	private String name;

	@Column(name = "Engine")
	private String engine;

	@Column(name = "Version")
	private String version;

	@Column(name = "Row_format")
	@JsonProperty(value = "Row_format")
	private Long rowFormat;

	@Column(name = "Rows")
	private Long rows;

	@Column(name = "Avg_row_length")
	@JsonProperty(value = "Avg_row_length")
	private Long avgRowLength;

	@Column(name = "Data_length")
	@JsonProperty(value = "Data_length")
	private Long dataLength;

	@Column(name = "Max_data_length")
	@JsonProperty(value = "Max_data_length")
	private Long maxDataLength;

	@Column(name = "Index_length")
	@JsonProperty(value = "Index_length")
	private Long indexLength;

	@Column(name = "Data_free")
	@JsonProperty(value = "Data_free")
	private String dataFree;

	@Column(name = "Auto_increment")
	@JsonProperty(value = "Auto_increment")
	private Long autoIncrement;

	@Column(name = "Create_time")
	@JsonProperty(value = "Create_time")
	private Date createTime;

	@Column(name = "Update_time")
	@JsonProperty(value = "Update_time")
	private Date updateTime;

	@Column(name = "Check_time")
	@JsonProperty(value = "Check_time")
	private Date checkTime;

	@Column(name = "Collation")
	private String collation;

	@Column(name = "Checksum")
	private String checksum;

	@Column(name = "Create_options")
	@JsonProperty(value = "Create_options")
	private String createOptions;

	@Column(name = "Comment")
	private String comment;

	@Column(name = "Max_index_length")
	@JsonProperty(value = "Max_index_length")
	private Long maxIndexLength;

	@Column(name = "Temporary")
	private String temporary;

}
