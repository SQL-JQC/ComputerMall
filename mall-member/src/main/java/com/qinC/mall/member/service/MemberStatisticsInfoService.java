package com.qinC.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 01:59:13
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

