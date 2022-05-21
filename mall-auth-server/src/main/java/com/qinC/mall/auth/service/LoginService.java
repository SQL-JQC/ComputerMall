package com.qinC.mall.auth.service;

import com.qinC.common.utils.R;
import com.qinC.mall.auth.vo.UserLoginVo;
import com.qinC.mall.auth.vo.UserRegisterVo;

public interface LoginService {

    R sendCode(String phone);

    R register(UserRegisterVo vo);

    R login(UserLoginVo vo);

}
