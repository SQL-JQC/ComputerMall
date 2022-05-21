package com.qinC.mall.search.service;

import com.qinC.common.to.es.SkuEsMode;

import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsMode> skuEsModes) throws IOException;

}
