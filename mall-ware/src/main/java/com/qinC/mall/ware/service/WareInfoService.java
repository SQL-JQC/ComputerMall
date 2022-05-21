package com.qinC.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.ware.entity.WareInfoEntity;
import com.qinC.mall.ware.vo.FareVo;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 02:25:19
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    FareVo getFare(Long addrId);

}

