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
import com.kao.server.util.properties.SmsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

/**
 * @author 全鸿润
 */
@Component
public class VerificationCodeGenerator {

    private static VerificationCodeGenerator verificationCodeGenerator;

    @Autowired
    private SmsProperties smsProperties;

    /**
     * 调用阿里云接口获取短信验证码
     *
     * @param phoneNumber 手机号
     * @return 短信验证码
     */
    public static String generateVerificationCode(String phoneNumber) {
        String verifiedCode = String.valueOf(new Random().nextInt(899999) + 100000);

        String accessKeyId = verificationCodeGenerator.smsProperties.accessKeyId;
        String secret = verificationCodeGenerator.smsProperties.accessSecret;
        //配置密钥
        DefaultProfile mProfile = DefaultProfile.getProfile(verificationCodeGenerator.smsProperties.regionId,
                accessKeyId, secret);
        IAcsClient mClient = new DefaultAcsClient(mProfile);
        //调用阿里云短信服务平台
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(verificationCodeGenerator.smsProperties.sysDomain);
        request.setSysVersion(verificationCodeGenerator.smsProperties.sysVersion);
        request.setSysAction(verificationCodeGenerator.smsProperties.sysAction);
        request.putQueryParameter("RegionId", verificationCodeGenerator.smsProperties.regionId);
        request.putQueryParameter("TemplateCode", verificationCodeGenerator.smsProperties.templateCode);
        request.putQueryParameter("SignName", verificationCodeGenerator.smsProperties.signName);
        request.putQueryParameter("TemplateType", verificationCodeGenerator.smsProperties.templateType.toString());
        request.putQueryParameter("TemplateName", verificationCodeGenerator.smsProperties.templateName);
        request.putQueryParameter("TemplateContent", verificationCodeGenerator.smsProperties.templateContent);
        request.putQueryParameter("Remark", verificationCodeGenerator.smsProperties.remark);
        request.putQueryParameter("PhoneNumbers", phoneNumber);
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

    @PostConstruct
    public void init() {
        verificationCodeGenerator = this;
        verificationCodeGenerator.smsProperties = this.smsProperties;
    }
}
