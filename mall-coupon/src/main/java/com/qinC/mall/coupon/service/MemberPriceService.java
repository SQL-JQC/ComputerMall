package com.qinC.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.coupon.entity.MemberPriceEntity;

import java.util.Map;

/**
 * 商品会员价格
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 01:36:43
 */
public interface MemberPriceService extends IService<MemberPriceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

