package com.qinC.mall.order.to;

import com.qinC.mall.order.entity.OrderEntity;
import com.qinC.mall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderCreateTo {

    private OrderEntity order;
    private List<OrderItemEntity> items;
    private BigDecimal payPrice;
    private BigDecimal fare;

}
