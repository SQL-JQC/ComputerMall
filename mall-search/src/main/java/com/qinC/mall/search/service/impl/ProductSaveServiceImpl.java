package com.qinC.mall.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.qinC.common.to.es.SkuEsMode;
import com.qinC.mall.search.config.ElasticSearchConfig;
import com.qinC.mall.search.constant.EsConstant;
import com.qinC.mall.search.service.ProductSaveService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuEsMode> skuEsModes) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsMode skuEsMode : skuEsModes) {
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuEsMode.getSkuId().toString());
            String s = JSON.toJSONString(skuEsMode);
            indexRequest.source(s, XContentType.JSON);

            bulkRequest.add(indexRequest);
        }
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        //通过返回的bulkResponse来分析ElasticSearch保存是否出错
        boolean b = bulkResponse.hasFailures();
        List<String> collect = Arrays.stream(bulkResponse.getItems()).map((item) -> {
            return item.getId();
        }).collect(Collectors.toList());
        log.error("商品上架错误：{}", collect);

        return b;
    }

}
