package com.qinC.mall.order.feign;

import com.qinC.common.to.SkuHasStockVo;
import com.qinC.common.utils.R;
import com.qinC.mall.order.vo.WareSkuLockVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(name = "mall-ware", fallback = WareFeignServiceImpl.class)
public interface WareFeignService {

    @PostMapping("/ware/waresku/hasStock")
    List<SkuHasStockVo> getSkusHasStock(@RequestBody List<Long> skuIds);

    @GetMapping("/ware/wareinfo/fare")
    R getFare(@RequestParam("addrId") Long addrId);

    @PostMapping("/ware/waresku/lock/order")
    R orderLockStock(@RequestBody WareSkuLockVo vo);

}
