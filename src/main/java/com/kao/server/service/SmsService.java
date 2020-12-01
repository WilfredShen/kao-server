package com.kao.server.service;

/**
 * @author 全鸿润
 */
public interface SmsService {
    /**
     * 获取验证码
     *
     * @param phoneNumber 手机号
     * @return 验证码
     */
    String getVerificationCode(String phoneNumber);
}
