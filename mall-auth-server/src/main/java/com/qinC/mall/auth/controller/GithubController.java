package com.qinC.mall.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.qinC.common.constant.AuthServerConstant;
import com.qinC.common.utils.HttpUtils;
import com.qinC.common.utils.R;
import com.qinC.mall.auth.feign.MemberFeignService;
import com.qinC.mall.auth.utils.WeiboAuthUtil;
import com.qinC.common.vo.MemberRespVo;
import com.qinC.mall.auth.vo.SocialUser;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GithubController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MemberFeignService memberFeignService;

    @GetMapping("/weibo/success")
    public String weibo(@RequestParam("code") String code, HttpSession session, HttpServletResponse servletResponse) throws Exception {
        Map<String, String> header = new HashMap<String, String>();
        Map<String, String> query = new HashMap<String, String>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("client_id", WeiboAuthUtil.CLIENT_ID);
        map.put("client_secret", WeiboAuthUtil.CLIENT_SECRET);
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", WeiboAuthUtil.FRONT_REDIRECT_URL);
        map.put("code", code);

        HttpResponse response = HttpUtils.doPost("https://api.weibo.com", "/oauth2/access_token", "post", header, query, map);
        if (response.getStatusLine().getStatusCode() == 200) {
            String json = EntityUtils.toString(response.getEntity());
            SocialUser socialUser = JSON.parseObject(json, SocialUser.class);

            //remote call
            R r = memberFeignService.Oauthlogin(socialUser);
            if (r.getCode() == 0) {
                MemberRespVo data = r.getData("data", new TypeReference<MemberRespVo>() {
                });
                session.setAttribute(AuthServerConstant.LOGIN_USER, data);

                return "redirect:http://qin.com";
            } else {
                return "redirect:http://auth.qin.com/login.html";
            }
        } else {
            return "redirect:http://auth.qin.com/login.html";
        }
    }

//    @GetMapping("/github/success")
//    public String github(@RequestParam("code") String code, HttpSession session) {
//        System.out.println("第三方登录github回调 code = " + code);
//
//        //请求access_token
//        String url = "https://github.com/login/oauth/access_token" +
//                "?client_id=" + GithubAuth.CLIENT_ID +
//                "&client_secret=" + GithubAuth.CLIENT_SECRET +
//                "&code=" + code +
//                "&grant_type=authorization_code" +
//                "&redirect_uri=" + GithubAuth.FRONT_REDIRECT_URL;
////        String response = restTemplate.postForObject(url, null, String.class);
////        String access_token = response.split("&")[0].split("=")[1];
//        // 构建请求头
//        HttpHeaders requestHeaders = new HttpHeaders();
//        // 指定响应返回json格式
//        requestHeaders.add("accept", "application/json");
//        // 构建请求实体
//        HttpEntity<String> requestEntity = new HttpEntity<String>(requestHeaders);
//        // post 请求方式
//        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
//        String responseStr = response.getBody();
//        // 解析响应json字符串
//        JSONObject jsonObject = JSONObject.parseObject(responseStr);
//        String access_token = jsonObject.getString("access_token");
//        System.out.println(access_token);
//
//        String url2 = "https://api.github.com/user";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("accept", "application/json");
//        httpHeaders.add("Authorization", "token " + access_token);
//        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url2, HttpMethod.GET, httpEntity, String.class);
//        String body = responseEntity.getBody();
//        System.out.println(body);
//        Map map = JSON.parseObject(body, Map.class);
//        String login = (String) map.get("login");
//        //将第三方登录的用户信息存入session
//        session.setAttribute("user", login);
//
//        return "redirect:http://qin.com";
//    }

//    @GetMapping("/github/success")
//    public String github(@RequestParam("code") String code, HttpSession session) throws IOException {
//        System.out.println("第三方登录github回调 code = " + code);
//
//        //请求access_token
//        String url = "https://gitee.com/oauth/access_token" +
//                "?client_id=" + GithubAuth.CLIENT_ID +
//                "&client_secret=" + GithubAuth.CLIENT_SECRET +
//                "&code=" + code +
//                "&grant_type=authorization_code" +
//                "&redirect_uri=" + GithubAuth.FRONT_REDIRECT_URL;
//
//        JSONObject accessTokenJson = getAccessToken(url);
//
//        url = "https://gitee.com/api/v5/user?access_token=" + accessTokenJson.get("access_token");
//        JSONObject jsonObject = getUserInfo(url);
//        System.out.println(jsonObject);
//
//        return "redirect:http://qin.com";
//    }

//    public JSONObject getAccessToken(String url) throws IOException {
//        HttpClient client = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
//        HttpResponse response = client.execute(httpPost);
//        HttpEntity entity = (HttpEntity) response.getEntity();
//        if (null != entity) {
//            String result = EntityUtils.toString((org.apache.http.HttpEntity) entity, "UTF-8");
//            return JSONObject.parseObject(result);
//        }
//        httpPost.releaseConnection();
//        return null;
//    }

//    public JSONObject getUserInfo(String url) throws IOException {
//        JSONObject jsonObject = null;
//        CloseableHttpClient client = HttpClients.createDefault();
//
//        HttpGet httpGet = new HttpGet(url);
//        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
//        HttpResponse response = client.execute(httpGet);
//        HttpEntity entity = (HttpEntity) response.getEntity();
//        if (entity != null) {
//            String result = EntityUtils.toString((org.apache.http.HttpEntity) entity, "UTF-8");
//            jsonObject = JSONObject.parseObject(result);
//        }
//
//        httpGet.releaseConnection();
//
//        return jsonObject;
//    }

}
