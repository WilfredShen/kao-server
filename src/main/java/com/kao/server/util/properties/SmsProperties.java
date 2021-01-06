package com.kao.server.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 全鸿润
 */

@Component
public class SmsProperties {

    @Value("${sms.access-key-id}")
    public String accessKeyId;

    @Value("${sms.access-secret}")
    public String accessSecret;

    @Value("${sms.region-id}")
    public String regionId;

    @Value("${sms.sys-domain}")
    public String sysDomain;

    @Value("${sms.sys-version}")
    public String sysVersion;

    @Value("${sms.sys-action}")
    public String sysAction;

    @Value("${sms.sign-name}")
    public String signName;

    @Value("${sms.template-code}")
    public String templateCode;

    @Value("${sms.template-type}")
    public Integer templateType;

    @Value("${sms.template-name}")
    public String templateName;

    public String templateContent = "您正在申请手LH出题系统手机注册，验证码为：${code}，5分钟内有效！";

    @Value("${sms.remark}")
    public String remark;

    @Override
    public String toString() {
        return "SmsProperties{" +
                "accessKeyId='" + accessKeyId + '\'' +
                ", accessSecret='" + accessSecret + '\'' +
                ", regionId='" + regionId + '\'' +
                ", sysDomain='" + sysDomain + '\'' +
                ", sysAction='" + sysAction + '\'' +
                ", signName='" + signName + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateContent='" + templateContent + '\'' +
                '}';
    }
}
