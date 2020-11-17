package com.kao.server.util.login;

import com.kao.server.entity.User;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

import java.sql.Timestamp;

public class RegisterChecker {

    public static JsonResult checkRegister(String username, String password, String phoneNumber, String verificationCode, LoginServiceImpl loginService) {

        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "密码不能为空");
        }
        if (phoneNumber == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空");
        }
        if (verificationCode == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODE_ISNULL, "验证码不能为空");
        }
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);
        User user = loginService.findUserNameByUsername(username);
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
