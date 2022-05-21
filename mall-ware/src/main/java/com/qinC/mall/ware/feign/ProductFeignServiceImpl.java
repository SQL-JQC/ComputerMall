package com.qinC.mall.ware.feign;

import com.qinC.common.to.SkuInfoTo;
import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

@Component
public class ProductFeignServiceImpl implements ProductFeignService {

    @Override
    public SkuInfoTo skuName(Long skuId) {
        return null;
    }

}
