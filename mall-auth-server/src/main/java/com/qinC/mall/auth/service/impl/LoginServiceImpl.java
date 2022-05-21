package com.qinC.mall.auth.service.impl;

import com.qinC.common.constant.AuthServerConstant;
import com.qinC.common.exception.BizCodeEnume;
import com.qinC.common.utils.R;
import com.qinC.mall.auth.feign.MemberFeignService;
import com.qinC.mall.auth.feign.ThirdPartyFeignService;
import com.qinC.mall.auth.service.LoginService;
import com.qinC.mall.auth.utils.RandomUtil;
import com.qinC.mall.auth.vo.UserLoginVo;
import com.qinC.mall.auth.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private ThirdPartyFeignService thirdPartyFeignService;

    @Autowired
    private MemberFeignService memberFeignService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public R sendCode(String phone) {
        //TODO
        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.CODE_CACHE_PREFIX + phone);
        if (!StringUtils.isEmpty(redisCode)) {
            long l = Long.parseLong(redisCode.split("_")[1]);
            if (System.currentTimeMillis() - l < 20000) {
                return R.error(BizCodeEnume.SMS_CODE_EXCEPTION.getCode(), BizCodeEnume.SMS_CODE_EXCEPTION.getMsg());
            }
        }

        //随机生成验证码
        String code = RandomUtil.getFourBitRandom();
        //给生成的验证码加上时间戳存入Redis中
        String code_Time = code + "_" + System.currentTimeMillis();
        redisTemplate.opsForValue().set(AuthServerConstant.CODE_CACHE_PREFIX + phone, code_Time, 5, TimeUnit.MINUTES);

        //remote call
        thirdPartyFeignService.sendCode(phone, code);

        return R.ok();
    }

    @Override
    public R register(UserRegisterVo vo) {
        String code = vo.getCode();

        String redisCode = redisTemplate.opsForValue().get(AuthServerConstant.CODE_CACHE_PREFIX + vo.getPhone());
        if (!StringUtils.isEmpty(redisCode)) {
            if (code.equals(redisCode.split("_")[0])) {
                //验证码正确
                redisTemplate.delete(AuthServerConstant.CODE_CACHE_PREFIX + vo.getPhone());
                //remote call
                R r = memberFeignService.register(vo);
                return r;
            } else {
                //验证码错误
                return R.error();
            }
        } else {
            //验证码过期
            return R.error();
        }
    }

    @Override
    public R login(UserLoginVo vo) {
        //remote call
        R r = memberFeignService.login(vo);

        return r;
    }

}
