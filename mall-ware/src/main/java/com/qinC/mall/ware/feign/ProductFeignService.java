package com.qinC.mall.ware.feign;

import com.qinC.common.to.SkuInfoTo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "mall-product", fallback = ProductFeignServiceImpl.class)
public interface ProductFeignService {

    @GetMapping("/product/skuinfo/skuName/{skuId}")
    SkuInfoTo skuName(@PathVariable("skuId") Long skuId);

}
