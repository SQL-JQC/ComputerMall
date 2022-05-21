package com.qinC.mall.auth.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WeiboAuthUtil implements InitializingBean {

    @Value("${auth.weibo.clientId}")
    private String clientId;

    @Value("${auth.weibo.clientSecret}")
    private String clientSecret;

    @Value("${auth.weibo.frontRedirectUrl}")
    private String frontRedirectUrl;

    public static String CLIENT_ID;
    public static String CLIENT_SECRET;
    public static String FRONT_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() throws Exception {
        CLIENT_ID = clientId;
        CLIENT_SECRET = clientSecret;
        FRONT_REDIRECT_URL = frontRedirectUrl;
    }

}
