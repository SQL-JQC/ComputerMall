package com.qinC.mall.auth.feign;

import com.qinC.common.utils.R;
import com.qinC.mall.auth.vo.SocialUser;
import com.qinC.mall.auth.vo.UserLoginVo;
import com.qinC.mall.auth.vo.UserRegisterVo;
import org.springframework.stereotype.Component;

@Component
public class MemberFeignServiceImpl implements MemberFeignService {

    @Override
    public R register(UserRegisterVo vo) {
        return null;
    }

    @Override
    public R login(UserLoginVo vo) {
        return null;
    }

    @Override
    public R Oauthlogin(SocialUser vo) throws Exception {
        return null;
    }

}
