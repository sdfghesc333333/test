package com.asia.leadsgen.test.repository;

import java.util.List;

import com.asia.leadsgen.test.model.ProductInfo;

public interface ProductRepository{

    public List<ProductInfo> getListProductByCampaignId(String campaignId);
}
