package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.service.impl.SmsServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.IsLoggedIn;
import com.kao.server.util.login.SaltGenerator;
import com.kao.server.util.login.UidGenerator;
import com.kao.server.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
@CrossOrigin
@RequestMapping(value = "/visitor")
public class LoginController {

    @Autowired
    LoginServiceImpl loginService;
    @Autowired
    SmsServiceImpl smsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "密码不能为空");
        }
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);
        User user = loginService.findUserByUsername(username);
        if (user != null && user.getUsername().equals(username)) {
            if (user.getPassword().equals(password)) {
                String token = TokenGenerator.generateToken(user.getUsername(), String.valueOf(user.getUid()));
                jsonResult.setStatus(JsonResultStatus.SUCCESS);
                jsonResult.setMessage(null);
                jsonResult.setData(token);
            } else {
                jsonResult.setStatus(JsonResultStatus.PASSWORD_WRONG);
                jsonResult.setMessage("密码错误");
            }
        } else {
            jsonResult.setStatus(JsonResultStatus.USERNAME_WRONG);
            jsonResult.setMessage("用户名错误");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
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

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String username = jsonObject.getString("username");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        String password = jsonObject.getString("password");
        String passwordAgain = jsonObject.getString("passwordAgain");
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

    @RequestMapping(value = "/getvfcode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
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
