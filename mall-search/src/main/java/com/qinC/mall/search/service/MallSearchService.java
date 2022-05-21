package com.qinC.mall.search.service;

import com.qinC.mall.search.vo.SearchParam;
import com.qinC.mall.search.vo.SearchResult;

public interface MallSearchService {

    SearchResult search(SearchParam param);

}
