package com.qinC.mall.search.controller;

import com.qinC.mall.search.service.MallSearchService;
import com.qinC.mall.search.vo.SearchParam;
import com.qinC.mall.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RefreshScope
public class SearchController {

    @Autowired
    private MallSearchService mallSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchParam param, Model model, HttpServletRequest request) {
        param.set_queryString(request.getQueryString());

        SearchResult result = mallSearchService.search(param);

        model.addAttribute("result", result);

        return "list";
    }

}
