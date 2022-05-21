package com.qinC.mall.auth.feign;

import com.qinC.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "mall-third-party", fallback = ThirdPartyFeignServiceImpl.class)
public interface ThirdPartyFeignService {

    @GetMapping("/msm/sendCode")
    R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);

}
