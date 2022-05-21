package com.qinC.mall.product.feign;

import com.qinC.common.to.es.SkuEsMode;
import com.qinC.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "mall-search", fallback = SearchFeignServiceImpl.class)
public interface SearchFeignService {

    @PostMapping("/search/save/product")
    R productStatusUp(@RequestBody List<SkuEsMode> skuEsModes);

}
