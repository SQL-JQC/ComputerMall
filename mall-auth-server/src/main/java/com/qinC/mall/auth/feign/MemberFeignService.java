package com.qinC.mall.auth.feign;

import com.qinC.common.utils.R;
import com.qinC.mall.auth.vo.SocialUser;
import com.qinC.mall.auth.vo.UserLoginVo;
import com.qinC.mall.auth.vo.UserRegisterVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "mall-member", fallback = MemberFeignServiceImpl.class)
public interface MemberFeignService {

    @PostMapping("/member/member/register")
    R register(@RequestBody UserRegisterVo vo);

    @PostMapping("/member/member/login")
    R login(@RequestBody UserLoginVo vo);

    @PostMapping("/member/member/oauth/login")
    R Oauthlogin(@RequestBody SocialUser vo) throws Exception;

}
