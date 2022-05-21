package com.qinC.mall.order.feign;

import com.qinC.mall.order.vo.OrderItemVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@FeignClient(name = "mall-cart", fallback = CartFeignServiceImpl.class)
public interface CartFeignService {

    @GetMapping("/currentUserCartItems")
    @ResponseBody
    List<OrderItemVo> getCurrentUserCartItems();

}
