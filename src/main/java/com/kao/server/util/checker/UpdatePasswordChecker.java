package com.kao.server.util.checker;

import cn.hutool.core.util.StrUtil;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

/**
 * @author 全鸿润
 */
public class UpdatePasswordChecker {

    public static JsonResult checkUpdatePassword(String username, String password, String passwordAgain, String phoneNumber, String verificationCode, LoginServiceImpl loginService) {

        if (StrUtil.isNotEmpty(username)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (StrUtil.isNotEmpty(phoneNumber)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空");
        }
        if (StrUtil.isNotEmpty(password)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "新密码不能为空");
        }
        if (StrUtil.isNotEmpty(passwordAgain)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "确认密码不能为空");
        }
        if (StrUtil.isNotEmpty(verificationCode)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODE_ISNULL, "验证码不能为空");
        }

        if (!password.equals(passwordAgain)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNOT_THESAME, "两次输入密码不一致");
        }
        int raws = loginService.updatePassword(username, password, phoneNumber, verificationCode, passwordAgain);
        if (raws == 1) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.SUCCESS, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UPDATE_PASSWORD_FAILED, "修改密码失败,请检查账号是否正确");
        }
    }
}
