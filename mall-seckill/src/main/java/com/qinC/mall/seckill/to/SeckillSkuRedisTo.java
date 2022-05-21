package com.qinC.mall.seckill.to;

import com.qinC.mall.seckill.vo.SkuInfoVo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SeckillSkuRedisTo {

    private Long id;

    private Long promotionId;

    private Long promotionSessionId;

    private Long skuId;

    private String randomCode;

    private BigDecimal seckillPrice;

    private int seckillCount;

    private int seckillLimit;

    private Integer seckillSort;

    private Long startTime;

    private Long endTime;

    //sku的详细信息
    private SkuInfoVo skuInfoVo;

}
