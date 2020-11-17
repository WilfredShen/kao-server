package com.kao.server.service;

/**
 * @author 全鸿润
 */
public interface SmsService {

    public String getVerificationCode(String phoneNumber);
}
