package com.asia.leadsgen.fmerch.repository;

import com.asia.leadsgen.fmerch.model.ProductInfo;

import java.util.List;

public interface ProductRepository{

    public List<ProductInfo> getListProductByCampaignId(String campaignId);
}
