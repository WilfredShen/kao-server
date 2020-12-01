package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.service.LoginService;
import com.kao.server.service.SmsService;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.SaltGenerator;
import com.kao.server.util.login.UidGenerator;
import com.kao.server.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

/**
 * @author 全鸿润
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/visitor")
public class LoginController {

    @Autowired
    LoginService loginService;
    @Autowired
    SmsService smsService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User user = loginService.findUserByUsername(username);
        JsonResult jsonResult = new JsonResult(null, null, null);
        int state = loginService.handleLogin(user, username, password);
        if (state == JsonResultStatus.SUCCESS) {
            String token = TokenGenerator.generateToken(
                    (user).getUsername(),
                    String.valueOf(user.getUid()),
                    (user).getPassword(),
                    (user).getAccountType()
            );
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            jsonResult.setStatus(state);
            Cookie tokenCookie = new Cookie("accessToken", token);
            Cookie uidCookie = new Cookie("uid", String.valueOf(user.getUid()));
            tokenCookie.setMaxAge(24 * 60 * 60);
            tokenCookie.setDomain("test.com");
            tokenCookie.setPath("/");
            uidCookie.setMaxAge(24 * 60 * 60);
            uidCookie.setPath("/");
            uidCookie.setDomain("test.com");
            response.addCookie(tokenCookie);
            response.addCookie(uidCookie);

        } else if (state == JsonResultStatus.USERNAME_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_WRONG_DESC);
        } else if (state == JsonResultStatus.USERNAME_ISNULL) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_ISNULL_DESC);
        } else if (state == JsonResultStatus.PASSWORD_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_WRONG_DESC);
        } else if (state == JsonResultStatus.PASSWORD_ISNULL) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_ISNULL_DESC);
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
        int state = loginService.handleRegister(
                username,
                password,
                phoneNumber,
                verificationCode
        );
        if (state == JsonResultStatus.SUCCESS) {
            User newUser = new User();
            newUser.setUid(UidGenerator.getUid());
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setPhone(phoneNumber);
            newUser.setSalt(SaltGenerator.getSalt());
            newUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));

            loginService.addOne(newUser);
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            JsonResult jsonResult = ResultFactory.buildFailJsonResult(state, null);
            if (state == JsonResultStatus.PHONE_NUMBER_EXISTED) {
                jsonResult.setMessage(JsonResultStatus.PHONE_NUMBER_EXISTED_DESC);
            } else if (state == JsonResultStatus.USERNAME_IS_EXITED) {
                jsonResult.setMessage(JsonResultStatus.USERNAME_IS_EXITED_DESC);
            } else if (state == JsonResultStatus.VERIFICATIONS_IS_WRONG) {
                jsonResult.setMessage(JsonResultStatus.VERIFICATIONS_IS_WRONG_DESC);
            }

            return jsonResult;
        }
    }

    @RequestMapping(value = "/update_password", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        String password = jsonObject.getString("password");
        String passwordAgain = jsonObject.getString("passwordAgain");

        int state = loginService.handleUpdateUserPassword(
                username,
                password,
                phoneNumber,
                verificationCode,
                passwordAgain
        );
        if (state == JsonResultStatus.SUCCESS) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UPDATE_PASSWORD_FAILED, JsonResultStatus.UPDATE_PASSWORD_FAILED_DESC);
        }
    }

    @RequestMapping(value = "/getvfcode", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = loginService.getVerificationCode(phoneNumber);
        if (verificationCode != null) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }
    }
}
