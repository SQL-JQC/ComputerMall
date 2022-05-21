package com.qinC.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qinC.common.utils.PageUtils;
import com.qinC.mall.member.entity.MemberEntity;
import com.qinC.mall.member.exception.PhoneExistException;
import com.qinC.mall.member.exception.UsernameExistException;
import com.qinC.mall.member.vo.MemberLoginVo;
import com.qinC.mall.member.vo.MemberRegisterVo;
import com.qinC.mall.member.vo.SocialUser;

import java.util.Map;

/**
 * 会员
 *
 * @author qinC
 * @email 2741118571@qq.com
 * @date 2021-12-08 01:59:14
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberRegisterVo vo);

    void checkPhoneUnique(String phone) throws PhoneExistException;

    void checkUsernameUnique(String username) throws UsernameExistException;

    MemberEntity login(MemberLoginVo vo);

    MemberEntity login(SocialUser vo) throws Exception;

}

