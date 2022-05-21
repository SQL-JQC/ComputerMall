package com.qinC.mall.thirdparty.controller;

import com.qinC.common.utils.R;
import com.qinC.mall.thirdparty.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class OssController {

    @Autowired
    private OssService ossService;

    @RequestMapping("/oss/policy")
    public R policy() {
        Map<String, String> respMap = ossService.policy();

        return R.ok().put("data", respMap);
    }

}
