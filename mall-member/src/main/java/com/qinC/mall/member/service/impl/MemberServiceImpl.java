package com.qinC.mall.member.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qinC.common.utils.HttpUtils;
import com.qinC.mall.member.entity.MemberLevelEntity;
import com.qinC.mall.member.exception.PhoneExistException;
import com.qinC.mall.member.exception.UsernameExistException;
import com.qinC.mall.member.service.MemberLevelService;
import com.qinC.mall.member.vo.MemberLoginVo;
import com.qinC.mall.member.vo.MemberRegisterVo;
import com.qinC.mall.member.vo.SocialUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qinC.common.utils.PageUtils;
import com.qinC.common.utils.Query;

import com.qinC.mall.member.dao.MemberDao;
import com.qinC.mall.member.entity.MemberEntity;
import com.qinC.mall.member.service.MemberService;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Autowired
    private MemberLevelService memberLevelService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void register(MemberRegisterVo vo) {
        MemberEntity memberEntity = new MemberEntity();

        MemberLevelEntity memberLevelEntity = memberLevelService.getDefaultLevel();
        memberEntity.setLevelId(memberLevelEntity.getId());

        checkPhoneUnique(vo.getPhone());
        checkUsernameUnique(vo.getUserName());
        memberEntity.setMobile(vo.getPhone());
        memberEntity.setUsername(vo.getUserName());

        memberEntity.setNickname(vo.getUserName());

        //密码要进行加密存储，加盐
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(vo.getPassword());
        memberEntity.setPassword(encode);

        baseMapper.insert(memberEntity);
    }

    @Override
    public void checkPhoneUnique(String phone) throws PhoneExistException {
        Integer mobile = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("mobile", phone));
        if (mobile > 0) {
            throw new PhoneExistException();
        }
    }

    @Override
    public void checkUsernameUnique(String username) throws UsernameExistException {
        Integer username1 = baseMapper.selectCount(new QueryWrapper<MemberEntity>().eq("username", username));
        if (username1 > 0) {
            throw new UsernameExistException();
        }
    }

    @Override
    public MemberEntity login(MemberLoginVo vo) {
        String loginacct = vo.getLoginacct();
        String password = vo.getPassword();

        MemberEntity entity = baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq("username", loginacct).or().eq("mobile", loginacct));
        if (entity == null) {
            return null;
        } else {
            String entityPassword = entity.getPassword();

            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            //将页面传来的明文密码与数据库中的加密密码进行匹配，看密码是否一致
            boolean matches = passwordEncoder.matches(password, entityPassword);
            if (matches) {
                return entity;
            } else {
                return null;
            }
        }
    }

    @Override
    public MemberEntity login(SocialUser vo) throws Exception {
        String uid = vo.getUid();

        MemberEntity memberEntity = baseMapper.selectOne(new QueryWrapper<MemberEntity>().eq("social_uid", uid));
        if (memberEntity != null) {
            MemberEntity update = new MemberEntity();
            update.setId(memberEntity.getId());
            update.setAccessToken(vo.getAccess_token());
            update.setExpiresIn(vo.getExpires_in());

            baseMapper.updateById(update);

            memberEntity.setAccessToken(vo.getAccess_token());
            memberEntity.setExpiresIn(vo.getExpires_in());

            return memberEntity;
        } else {
            MemberEntity register = new MemberEntity();

            try {
                Map<String, String> query = new HashMap<String, String>();
                query.put("access_token", vo.getAccess_token());
                query.put("uid", vo.getUid());
                HttpResponse response = HttpUtils.doGet("https://api.weibo.com", "/2/users/show.json", "get", new HashMap<String, String>(), query);
                if (response.getStatusLine().getStatusCode() == 200) {
                    String json = EntityUtils.toString(response.getEntity());
                    JSONObject jsonObject = JSON.parseObject(json);
                    String name = jsonObject.getString("name");
                    String gender = jsonObject.getString("gender");

                    register.setNickname(name);
                    register.setGender("m".equals(gender) ? 1 : 0);
                }
            } catch (Exception e) {}

            register.setSocialUid(vo.getUid());
            register.setAccessToken(vo.getAccess_token());
            register.setExpiresIn(vo.getExpires_in());

            baseMapper.insert(register);

            return register;
        }
    }

}