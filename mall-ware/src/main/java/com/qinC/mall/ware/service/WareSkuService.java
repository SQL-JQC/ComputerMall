package com.qinC.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.to.mq.OrderTo;
import com.qinC.common.to.mq.StockLockedTo;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.ware.entity.WareSkuEntity;
import com.qinC.mall.ware.vo.SkuHasStockVo;
import com.qinC.mall.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 02:25:18
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds);

    Boolean orderLockStock(WareSkuLockVo vo);

    void unlockStock(StockLockedTo to);

    void unlockStock(OrderTo orderTo);

}
