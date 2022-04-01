package com.asia.leadsgen.fmerch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_URL")
@Data
public class UrlInfo {

	@Id
    @Column(name = "S_ID")
    private String id;

    @Column(name = "S_DOMAIN_ID")
    @JsonProperty(value = "domain_id")
    private String domainId;
    
    @Column(name = "S_REF")
    private String ref;
    
    @Column(name = "S_URI")
    private String uri;
}
