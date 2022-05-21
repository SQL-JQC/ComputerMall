package com.qinC.mall.product.feign;

import com.qinC.common.exception.BizCodeEnume;
import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

@Component
public class SeckillFeignServiceImpl implements SeckillFeignService {

    @Override
    public R getSkuSeckillInfo(Long skuId) {
        return R.error(BizCodeEnume.TOO_MANY_REQUEST.getCode(), BizCodeEnume.TOO_MANY_REQUEST.getMsg());
    }

}
