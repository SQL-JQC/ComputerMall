package com.qinC.mall.thirdparty.controller;

import com.qinC.common.utils.R;
import com.qinC.mall.thirdparty.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class MsmController {

    @Autowired
    private MsmService msmService;

    @GetMapping("/msm/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {
        msmService.sendCode(phone, code);

        return R.ok();
    }

}
