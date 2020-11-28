package com.kao.server.service;

/**
 * @author 全鸿润
 */
public interface SmsService {
    /**
     * 获取验证码
     *
     * @param phoneNumber
     * @return 验证码
     */
    public String getVerificationCode(String phoneNumber);
}
