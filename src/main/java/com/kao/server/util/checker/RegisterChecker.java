package com.kao.server.util.checker;

import cn.hutool.core.util.StrUtil;
import com.kao.server.entity.User;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.SaltGenerator;
import com.kao.server.util.login.UidGenerator;

import java.sql.Timestamp;

public class RegisterChecker {

    public static JsonResult checkRegister(User user, String username, String password, String phoneNumber, String verificationCode, LoginServiceImpl loginService) {

        if (StrUtil.isNotEmpty(username)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (StrUtil.isNotEmpty(password)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "密码不能为空");
        }
        if (StrUtil.isNotEmpty(phoneNumber)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空");
        }
        if (StrUtil.isNotEmpty(verificationCode)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODE_ISNULL, "验证码不能为空");
        }
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);

        if (user == null) {
            if ((loginService.findPhoneNumberByPhoneNumber(phoneNumber) != null)) {
                jsonResult.setStatus(JsonResultStatus.PHONENUMBER_ISEXITED);
                jsonResult.setMessage("该手机号已经存在");
            } else {
                User newUser = new User();
                newUser.setUid(UidGenerator.getUID());
                newUser.setUsername(username);
                newUser.setPassword(password);
                newUser.setPhone(phoneNumber);
                newUser.setSalt(SaltGenerator.getSalt());
                newUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));
                loginService.addOne(newUser);
                jsonResult.setStatus(JsonResultStatus.SUCCESS);
            }

        } else {
            jsonResult.setStatus(JsonResultStatus.PHONENUMBER_ISEXITED);
            jsonResult.setMessage("用户名已经存在");
        }
        return jsonResult;

    }
}
