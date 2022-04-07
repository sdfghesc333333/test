package com.asia.leadsgen.test.repository;

import org.springframework.stereotype.Repository;

import com.asia.leadsgen.test.model.ProductInfo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @PersistenceContext
    EntityManager entityManager;

    @SuppressWarnings("unchecked")
	@Override
    public List<ProductInfo> getListProductByCampaignId(String campaignId) {
        String sql = "select product.S_ID as id, product.S_NAME as name, product.S_CAMPAIGN_ID," +
                "variant.S_FRONT_IMG_URL as img_front, variant.S_BACK_IMG_URL as img_back, price.PAYOUT " +
                "from (select s_id, S_NAME, S_CAMPAIGN_ID from tb_product where s_campaign_id = :id) product " +
                "join tb_product_variant variant on variant.s_product_id = product.s_id and variant.n_default = 1 " +
                "join (select s_product_id, min(S_SALE_PRICE - S_BASE_COST) as payout " +
                "from tb_product_price group by s_product_id)  price on price.s_product_id = product.s_id ";

        Query q = entityManager.createNativeQuery(sql, ProductInfo.class);
        q.setParameter("id", campaignId);

        return (List<ProductInfo>) q.getResultList();
    }
}
