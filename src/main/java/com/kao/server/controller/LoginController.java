package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.service.impl.SmsServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.*;
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

        return LoginChecker.checkLogin(username, password, loginService);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");

        return RegisterChecker.checkRegister(username, password, phoneNumber, verificationCode, loginService);
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

        return UpdatePasswordChecker.checkUpdatePassword(username, password, passwordAgain, phoneNumber, verificationCode, loginService);
    }

    @RequestMapping(value = "/getvfcode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);

        return GetVerificationCodeChecker.checkGetVerificationCode(phoneNumber, loginService, smsService);
    }
}
