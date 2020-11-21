package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.service.impl.SmsServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.login.IsLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

        return loginService.login(jsonObject, request);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        return loginService.register(jsonObject, request);
    }

    @RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        return loginService.updatePassword(jsonObject, request);
    }

    @RequestMapping(value = "/getvfcode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        return loginService.getVerificationCode(jsonObject, request);
    }
}
