package com.kao.server.util.login;

import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.service.impl.SmsServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

public class GetVerificationCodeChecker {

    public static JsonResult checkGetVerificationCode(String phoneNumber, LoginServiceImpl loginService, SmsServiceImpl smsService) {

        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);
        if (phoneNumber == null) {
            jsonResult.setStatus(JsonResultStatus.PHONENUMBER_ISNULL);
            jsonResult.setMessage("手机号不能为空");
            return jsonResult;
        }
        String verificationCode = smsService.getVerificationCode(phoneNumber);
        if (verificationCode != null) {
            jsonResult.setStatus(JsonResultStatus.SUCCESS);
        } else {
            jsonResult.setStatus(JsonResultStatus.VERIFICATIONCODE_GET_FAILED);
            jsonResult.setMessage("获取验证码失败!请检查手机号是否正确");
        }
        return jsonResult;
    }
}
