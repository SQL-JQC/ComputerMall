package com.qinC.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.product.entity.CommentReplayEntity;

import java.util.Map;

/**
 * 商品评价回复关系
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-07 20:50:26
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

