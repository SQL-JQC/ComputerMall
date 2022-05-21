package com.qinC.mall.search.vo;

import com.qinC.common.to.es.SkuEsMode;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResult {

    private List<SkuEsMode> products;

    private Integer pageNum;

    private Long total;

    private Integer totalPages;

    private List<Integer> pageNavs;

    private List<BrandVo> brands;

    private List<CatalogVo> catalogs;

    private List<AttrVo> attrs;

    private List<NavVo> navs = new ArrayList<NavVo>();

    private List<Long> attrIds = new ArrayList<Long>();

    @Data
    public static class BrandVo {
        private Long brandId;
        private String brandName;
        private String brandImg;
    }

    @Data
    public static class CatalogVo {
        private Long catalogId;
        private String catalogName;
    }

    @Data
    public static class AttrVo {
        private Long attrId;
        private String attrName;
        private List<String> attrValue;
    }

    @Data
    public static class NavVo {
        private String navName;
        private String navValue;
        private String link;
    }

}
