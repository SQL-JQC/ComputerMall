package com.qinC.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.product.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-07 20:50:26
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void updateDetail(BrandEntity brand);

    void removeRelation(List<Long> asList);

    List<BrandEntity> getBrandsByIds(List<Long> brandIds);

}

