package com.qinC.mall.product.feign;

import com.qinC.common.to.SkuReductionTo;
import com.qinC.common.to.SpuBoundTo;
import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

@Component
public class CouponFeignServiceImpl implements CouponFeignService {

    @Override
    public R saveSpuBounds(SpuBoundTo spuBoundTo) {
        return null;
    }

    @Override
    public R saveSkuReduction(SkuReductionTo skuReductionTo) {
        return null;
    }

}
