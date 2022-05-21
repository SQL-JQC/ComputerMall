package com.qinC.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.product.entity.SpuInfoDescEntity;
import com.qinC.mall.product.entity.SpuInfoEntity;
import com.qinC.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-07 20:50:25
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveSpuInfo(SpuSaveVo vo);

    void saveBaseSpuInfo(SpuInfoEntity spuInfoEntity);

    PageUtils queryPageByCondition(Map<String, Object> params);

    void spuUp(Long spuId);

    SpuInfoEntity getSpuInfoBySkuId(Long skuId);

}

