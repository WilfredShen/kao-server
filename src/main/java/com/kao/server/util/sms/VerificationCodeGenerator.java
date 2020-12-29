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

/**
 * @author 全鸿润
 */
public class VerificationCodeGenerator {

    /**
     * 调用阿里云接口获取短信验证码
     *
     * @param phoneNumber 手机号
     * @return 短信验证码
     */
    public static String generateVerificationCode(String phoneNumber) {
        String verifiedCode = String.valueOf(new Random().nextInt(899999) + 100000);
        //配置密钥
        DefaultProfile mProfile = DefaultProfile.getProfile("cn-hangzhou",
                "YourAccessKey", "YourAccessSecret");
        IAcsClient mClient = new DefaultAcsClient(mProfile);
        //调用阿里云短信服务平台
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("TemplateCode", "SMS_207953773");
        //0为验证码形式
        request.putQueryParameter("TemplateType", "0");
        request.putQueryParameter("TemplateName", "教育部学科评估管理信息系统");
        request.putQueryParameter("TemplateContent", "您的验证码${code}，该验证码5分钟内有效，请勿泄漏于他人！");
        request.putQueryParameter("Remark", "个人网站开发使用");
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + verifiedCode + "\"}");

        try {
            CommonResponse response = mClient.getCommonResponse(request);
            JSONObject verification = JSON.parseObject(response.getData());
            String successCode = "OK";
            String code = "Code";
            if (verification == null || !successCode.equals(verification.get(code))) {
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
