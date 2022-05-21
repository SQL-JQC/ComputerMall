package com.qinC.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.order.entity.OrderReturnReasonEntity;

import java.util.Map;

/**
 * 退货原因
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 02:14:44
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

