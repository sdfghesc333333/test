package com.asia.leadsgen.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asia.leadsgen.test.model.ProductInfo;
import com.asia.leadsgen.test.repository.ProductRepository;
import com.asia.leadsgen.test.util.GetterUtil;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductInfo> getProductByCampId(String campId){
        List<ProductInfo> listProduct = productRepository.getListProductByCampaignId(campId);
        listProduct.forEach(productInfo -> {
            Double payout = (Double.parseDouble(productInfo.getPayoutProfit())* 0.95)*0.4;
            String payoutProfit = String.valueOf(GetterUtil.formatDouble(payout, 2));
            productInfo.setPayoutProfit(payoutProfit);
        });
        return listProduct;

    }
}
