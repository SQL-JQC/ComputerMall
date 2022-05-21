package com.qinC.mall.product.vo;

import com.qinC.mall.product.entity.SkuImagesEntity;
import com.qinC.mall.product.entity.SkuInfoEntity;
import com.qinC.mall.product.entity.SpuInfoDescEntity;
import lombok.Data;

import java.util.List;

@Data
public class SkuItemVo {

    private SkuInfoEntity info;

    private boolean hasStock = true;

    private List<SkuImagesEntity> images;

    private List<SkuItemSaleAttrVo> saleAttr;

    private SpuInfoDescEntity desp;

    private List<SpuItemAttrGroupVo> groupAttrs;

    private SeckillInfoVo seckillInfoVo;

}
