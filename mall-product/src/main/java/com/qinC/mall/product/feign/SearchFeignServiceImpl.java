package com.qinC.mall.product.feign;

import com.qinC.common.to.es.SkuEsMode;
import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchFeignServiceImpl implements SearchFeignService {

    @Override
    public R productStatusUp(List<SkuEsMode> skuEsModes) {
        return null;
    }

}
