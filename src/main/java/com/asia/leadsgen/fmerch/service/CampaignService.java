package com.asia.leadsgen.fmerch.service;

import com.asia.leadsgen.fmerch.model.CampaignInfo;
import com.asia.leadsgen.fmerch.model.FmerchCampaignInfo;
import com.asia.leadsgen.fmerch.model.ProductInfo;
import com.asia.leadsgen.fmerch.repository.CampaignRepository;
import com.asia.leadsgen.fmerch.repository.FmerchCampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignService {
    @Autowired
    FmerchCampaignRepository fmerchCampaignRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    CategoryService categoryService;


    public FmerchCampaignInfo getFmerchCampByCampId(String campId) {
        FmerchCampaignInfo fmerchCampaignInfo = fmerchCampaignRepository.findFmerchCampaignInfoByCampaignId(campId);
        return fmerchCampaignInfo;
    }


    public FmerchCampaignInfo getFmerchCampById(String id) {
        FmerchCampaignInfo fmerchCampaignInfo = fmerchCampaignRepository.findFmerchCampaignInfoById(id);
        if (fmerchCampaignInfo != null) {
            String campId = fmerchCampaignInfo.getCampaignId();

            String category = categoryService.getCategoryByCampId(campId);
            fmerchCampaignInfo.setCategory(category);

            String url = fmerchCampaignInfo.getCampaignUrl();
            String domain = fmerchCampaignInfo.getDomain();
            String link = "https://" + domain + "/shop/" + url;
            fmerchCampaignInfo.setLink(link);

            List<ProductInfo> listProduct = productService.getProductByCampId(campId);
            fmerchCampaignInfo.setProducts(listProduct);
        }

        return fmerchCampaignInfo;
    }


	public List<CampaignInfo> findAllCampaign(){
		return campaignRepository.findAll();
	}

	public CampaignInfo findByCampaignId(String id) {
		Optional<CampaignInfo> campaign = campaignRepository.findById(id);
		CampaignInfo result = campaign.get();
		return result;
	}

}
