package com.qinC.mall.product.service.impl;

import com.qinC.mall.product.entity.CategoryBrandRelationEntity;
import com.qinC.mall.product.service.CategoryBrandRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinC.common.utils.PageUtils;
import com.qinC.common.utils.Query;

import com.qinC.mall.product.dao.BrandDao;
import com.qinC.mall.product.entity.BrandEntity;
import com.qinC.mall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<BrandEntity>();
        if (!StringUtils.isEmpty(key)) {
            wrapper.eq("brand_id", key).or().like("name", key);
        }

        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                wrapper
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void updateDetail(BrandEntity brand) {
        this.updateById(brand);
        if (!StringUtils.isEmpty(brand.getName())) {
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            //TODO
        }
    }

    @Transactional
    @Override
    public void removeRelation(List<Long> asList) {
        this.removeByIds(asList);

        int count = categoryBrandRelationService.count(new QueryWrapper<CategoryBrandRelationEntity>().in("brand_id", asList));
        if (count > 0) {
            categoryBrandRelationService.removeByIds(asList);
        }
    }

    @Cacheable(value = "brand", key = "'brandInfo:'+#root.args[0]")
    @Override
    public List<BrandEntity> getBrandsByIds(List<Long> brandIds) {
        List<BrandEntity> brands = baseMapper.selectList(new QueryWrapper<BrandEntity>().in("brand_id", brandIds));

        return brands;
    }

}