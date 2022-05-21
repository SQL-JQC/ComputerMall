package com.qinC.mall.product;

import com.qinC.mall.product.entity.BrandEntity;
import com.qinC.mall.product.service.BrandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    private BrandService brandService;

    @Test
    public void contextLoads() {
        BrandEntity brandEntity = new BrandEntity();
        brandEntity.setDescript("雷蛇灵刃笔记本电脑");
        brandEntity.setName("雷蛇灵刃");
        brandService.save(brandEntity);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void redis() {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();

        ops.set("hello", "world");

        String hello = ops.get("hello");
        System.out.println("保存的数据是" + hello);
    }

}
