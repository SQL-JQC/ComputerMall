package com.qinC.mall.auth.feign;

import com.qinC.common.utils.R;
import org.springframework.stereotype.Component;

@Component
public class ThirdPartyFeignServiceImpl implements ThirdPartyFeignService {

    @Override
    public R sendCode(String phone, String code) {
        return null;
    }

}
