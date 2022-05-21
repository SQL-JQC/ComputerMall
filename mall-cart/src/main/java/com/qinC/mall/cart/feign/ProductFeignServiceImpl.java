package com.qinC.mall.cart.feign;

import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFeignServiceImpl implements ProductFeignService {

    @Override
    public R info(Long skuId) {
        return null;
    }

    @Override
    public List<String> getSkuSaleAttrValues(Long skuId) {
        return null;
    }

    @Override
    public R getPrice(Long skuId) {
        return null;
    }

}
