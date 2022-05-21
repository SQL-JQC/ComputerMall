package com.qinC.mall.order.feign;

import com.qinC.mall.order.vo.OrderItemVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartFeignServiceImpl implements CartFeignService {

    @Override
    public List<OrderItemVo> getCurrentUserCartItems() {
        return null;
    }

}
