package com.qinC.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeckillInfoVo {

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

}
