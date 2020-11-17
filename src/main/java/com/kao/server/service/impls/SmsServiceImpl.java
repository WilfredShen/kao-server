package com.kao.server.service.impls;

import com.kao.server.service.SmsService;
import com.kao.server.util.sms.VerificationCodeGenerator;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Override
    public String getVerificationCode(String phoneNumber) {
        return VerificationCodeGenerator.generateVerificationCode(phoneNumber);
    }
}
