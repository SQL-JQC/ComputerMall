package com.qinC.mall.thirdparty.service.impl;

import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.qinC.mall.thirdparty.service.MsmService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class MsmServiceImpl implements MsmService {

    @Value("${msm.rlyun.serverIP}")
    private String serverIP;

    @Value("${msm.rlyun.serverPort}")
    private String serverPort;

    @Value("${msm.rlyun.accountSID}")
    private String accountSID;

    @Value("${msm.rlyun.accountToken}")
    private String accountToken;

    @Value("${msm.rlyun.appID}")
    private String appID;

    @Override
    public void sendCode(String phone, String code) {
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();

        sdk.init(serverIP, serverPort);
        sdk.setAccount(accountSID, accountToken);
        sdk.setAppId(appID);
        sdk.setBodyType(BodyType.Type_JSON);

        String to = phone;
        String templateId = "1";
        String[] datas = {code, "5"};
//        String subAppend = "1234";  //可选 扩展码，四位数字 0~9999
//        String reqId = "fadfafas";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas);
//        HashMap<String, Object> result = sdk.sendTemplateSMS(to, templateId, datas, subAppend, reqId);
        if ("000000".equals(result.get("statusCode"))) {
            //正常返回输出data包体信息（map）
            HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for (String key : keySet) {
                Object object = data.get(key);
                System.out.println(key + " = " + object);
            }
        } else {
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        }
    }

}
