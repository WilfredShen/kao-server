package com.kao.server.util.checker;

import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

public class UpdatePasswordChecker {

    public static JsonResult checkUpdatePassword(String username, String password, String passwordAgain, String phoneNumber, String verificationCode, LoginServiceImpl loginService) {

        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (phoneNumber == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "新密码不能为空");
        }
        if (passwordAgain == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "确认密码不能为空");
        }
        if (verificationCode == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODE_ISNULL, "验证码不能为空");
        }

        if (!password.equals(passwordAgain)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNOT_THESAME, "两次输入密码不一致");
        }
        int raws = loginService.updatePassword(username, password);
        if (raws == 1) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.SUCCESS, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UPDATE_PASSWORD_FAILED, "修改密码失败,请检查账号是否正确");
        }
    }
}
