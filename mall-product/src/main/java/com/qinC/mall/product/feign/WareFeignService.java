package com.qinC.mall.product.feign;

import com.qinC.common.to.SkuHasStockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "mall-ware", fallback = WareFeignServiceImpl.class)
public interface WareFeignService {

    @PostMapping("/ware/waresku/hasStock")
    List<SkuHasStockVo> getSkusHasStock(@RequestBody List<Long> skuIds);

}
