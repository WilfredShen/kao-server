package com.kao.server.util.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

public class VerificationCodeGenerator {

    public static String generateVerificationCode(String phoneNumber) {
        String verifiedCode = String.valueOf(new Random().nextInt(899999) + 100000);
        //配置密钥
        DefaultProfile m_profile = DefaultProfile.getProfile("cn-hangzhou",
                "YourAccessKey", "YourAccessSecret");
        IAcsClient m_client = new DefaultAcsClient(m_profile);

        //调用阿里云短信服务平台
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", "自动生成试卷系统");
        request.putQueryParameter("TemplateCode", "SMS_204116418");
        //0为验证码形式
        request.putQueryParameter("TemplateType", "0");
        request.putQueryParameter("TemplateName", "自动生成试卷系统");
        request.putQueryParameter("TemplateContent", "您正在申请手机注册，验证码为：${code}，5分钟内有效！");
        request.putQueryParameter("Remark", "个人网站开发测试");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifiedCode + "\"}");

        try {
            CommonResponse response = m_client.getCommonResponse(request);
            JSONObject verifiedCodeJSON = JSON.parseObject(response.getData());
            if (verifiedCodeJSON == null || !verifiedCodeJSON.get("Code").equals("OK")) {
                return null;
            }
            return verifiedCode;
        } catch (ClientException e) {
            e.printStackTrace();
            System.err.println("获取验证码失败,检查手机号是否输入正确");
            return null;
        }
    }
}
