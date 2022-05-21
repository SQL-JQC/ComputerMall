package com.qinC.mall.search.feign;

import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductFeignServiceImpl implements ProductFeignService {

    @Override
    public R getAttrsInfo(Long attrId) {
        return null;
    }

    @Override
    public R getBrandInfo(List<Long> brandIds) {
        return null;
    }

}
