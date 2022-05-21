package com.qinC.common.to.mq;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class StockDetailTo {
    /**
     * id
     */
    @TableId
    private Long id;

    private Long skuId;

    private String skuName;

    private Integer skuNum;

    private Long taskId;

    private Long wareId;

    private Integer lockStatus;

}
