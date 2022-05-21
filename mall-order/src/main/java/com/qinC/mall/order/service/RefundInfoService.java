package com.qinC.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.order.entity.RefundInfoEntity;

import java.util.Map;

/**
 * 退款信息
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 02:14:43
 */
public interface RefundInfoService extends IService<RefundInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

