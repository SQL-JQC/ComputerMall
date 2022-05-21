package com.qinC.mall.order.feign;

import com.qinC.common.to.SkuHasStockVo;
import com.qinC.common.utils.R;
import com.qinC.mall.order.vo.WareSkuLockVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WareFeignServiceImpl implements WareFeignService {

    @Override
    public List<SkuHasStockVo> getSkusHasStock(List<Long> skuIds) {
        return null;
    }

    @Override
    public R getFare(Long addrId) {
        return null;
    }

    @Override
    public R orderLockStock(WareSkuLockVo vo) {
        return null;
    }

}
