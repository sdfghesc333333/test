package com.asia.leadsgen.test.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@Data
public class ProductInfo {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_front")
    @JsonProperty("img_front_url")
    private String imgFrontUrl;

    @Column(name = "img_back")
    @JsonProperty("img_back_url")
    private String imgBackUrl;

    @Column(name = "payout")
    @JsonProperty("payout_profit")
    private String payoutProfit;

}
