package com.qinC.mall.product.controller;

import java.util.Arrays;
import java.util.Map;

import com.qinC.common.to.SkuInfoTo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import com.qinC.mall.product.entity.SkuInfoEntity;
import com.qinC.mall.product.service.SkuInfoService;
import com.qinC.common.utils.PageUtils;
import com.qinC.common.utils.R;

/**
 * sku信息
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-07 20:50:26
 */
@RestController
@RequestMapping("product/skuinfo")
@RefreshScope
public class SkuInfoController {

    @Autowired
    private SkuInfoService skuInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = skuInfoService.queryPageByCondition(params);

        return R.ok().put("page", page);
    }

    @GetMapping("/{skuId}/price")
    public R getPrice(@PathVariable("skuId") Long skuId){
        SkuInfoEntity byId = skuInfoService.getById(skuId);

        return R.ok().setData(byId.getPrice().toString());
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{skuId}")
    public R info(@PathVariable("skuId") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);

        return R.ok().put("skuInfo", skuInfo);
    }

    @GetMapping("/skuName/{skuId}")
    public SkuInfoTo skuName(@PathVariable("skuId") Long skuId) {
        SkuInfoEntity skuInfo = skuInfoService.getById(skuId);
        SkuInfoTo skuInfoTo = new SkuInfoTo();
        BeanUtils.copyProperties(skuInfo, skuInfoTo);

        return skuInfoTo;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.save(skuInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SkuInfoEntity skuInfo) {
        skuInfoService.updateById(skuInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] skuIds) {
        skuInfoService.removeByIds(Arrays.asList(skuIds));

        return R.ok();
    }

}
