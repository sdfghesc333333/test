package com.asia.leadsgen.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.CampaignInfo;
import com.asia.leadsgen.test.model.CategoryInfo;
import com.asia.leadsgen.test.repository.CampaignRepository;
import com.asia.leadsgen.test.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public String getCategoryByCampId(String campId){
        final String[] category = {""};
        String result = "";
        CampaignInfo campaign = campaignRepository.findCampaignInfoById(campId);
        if(campaign != null){
            String listIdCategory = campaign.getCategoryId();
            if(listIdCategory != null){
                String[] arrCategoryId = listIdCategory.split(",");

                List<String> categoryIds = Arrays.asList(arrCategoryId);
                List<CategoryInfo> categoryInfos = categoryRepository.findAllById(categoryIds);

                categoryInfos.forEach(categoryInfo -> {
                    category[0] = category[0] + categoryInfo.getName() + "/ ";
                });
                result = category[0].substring(0, category[0].length() - 2);
            }
        }
        return result;
    }
}
