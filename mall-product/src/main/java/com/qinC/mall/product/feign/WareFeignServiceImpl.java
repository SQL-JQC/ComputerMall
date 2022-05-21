package com.qinC.mall.product.feign;

import com.qinC.common.to.SkuHasStockVo;
import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WareFeignServiceImpl implements WareFeignService {

    @Override
    public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {
        return null;
    }

}
