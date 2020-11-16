package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.service.impls.LoginServiceImpl;
import com.kao.server.service.impls.SmsServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.SaltGenerator;
import com.kao.server.util.login.UidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAMEISNULL, "用户名不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDISNULL, "密码不能为空");
        }
        User user = loginService.findUserByUsername(username);

        if (user != null && user.getUsername().equals(username)) {
            if (user.getPassword().equals(password)) {
                return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS, null, user);
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDWRONG, "密码错误");
            }
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAMEWRONG, "用户名错误");
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAMEISNULL, "用户名不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDISNULL, "密码不能为空");
        }
        if (phoneNumber == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBERISNULL, "手机号不能为空");
        }
        if (verificationCode == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODEISNULL, "验证码不能为空");
        }
        User user = loginService.findUserNameByUsername(username);
        if (user == null) {
            if ((loginService.findPhoneNumberByPhoneNumber(phoneNumber)!=null)){
                return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBERISEXITED, "该手机号已经存在");
            }
            loginService.addOne(new User(UidGenerator.getUID(), username, password, SaltGenerator.getSalt(), phoneNumber));
            return ResultFactory.buildJsonResult(JsonResultStatus.SUCCESS,null,user);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAMEISEXITED, "用户名已经存在");
        }
    }

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String username = jsonObject.getString("username");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        String password = jsonObject.getString("password");
        String passwordAgain = jsonObject.getString("passwordAgain");
        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAMEISNULL, "用户名不能为空");
        }
        if (phoneNumber == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBERISNULL, "手机号不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDISNULL, "新密码不能为空");
        }
        if (passwordAgain == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDISNULL, "确认密码不能为空");
        }
        if (verificationCode == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODEISNULL, "验证码不能为空");
        }

        if (!password.equals(passwordAgain)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORDISNOTTHESAME, "两次输入密码不一致");
        }
        int raws = loginService.updatePassword(username, password);
        if (raws == 1) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.SUCCESS, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UPDATEPASSWORDFAILED, "修改密码失败,请检查账号是否正确");
        }
    }

    @RequestMapping(value = "/getvfcode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        if (phoneNumber == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBERISNULL, "手机号不能为空");
        }
        String verificationCode = smsService.getVerificationCode(phoneNumber);
        if (verificationCode!=null){
            return ResultFactory.buildFailJsonResult(JsonResultStatus.SUCCESS, null);
        }
        return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONCODEGETFAILED,"获取验证码失败!请检查手机号是否正确");
    }
}
