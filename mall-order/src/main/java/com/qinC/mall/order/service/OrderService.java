package com.qinC.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.to.mq.SeckillOrderTo;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.order.entity.OrderEntity;
import com.qinC.mall.order.vo.*;

import java.text.ParseException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 02:14:44
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

    SubmitOrderResponseVo submitOrder(OrderSubmitVo vo);

    OrderEntity getOrderByOrderSn(String orderSn);

    void closeOrder(OrderEntity entity);

    PayVo getOrderPay(String orderSn);

    PageUtils queryPageWithItem(Map<String, Object> params);

    String handlePayResult(PayAsyncVo vo) throws ParseException;

    void createSeckillOrder(SeckillOrderTo orderTo);

}
