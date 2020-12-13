package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.service.LoginService;
import com.kao.server.service.SmsService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.DigestGenerator;
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
    private LoginService loginService;
    @Autowired
    private SmsService smsService;

    @PostMapping("/login")
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        User user = loginService.findUserByUsername(username);
        JsonResult jsonResult = null;
        int state = loginService.handleLogin(user, username, password);
        if (state == JsonResultStatus.SUCCESS) {
            String token = TokenGenerator.generateToken(
                    (user).getUsername(),
                    String.valueOf(user.getUid()),
                    (user).getPassword()
            );
            session.setAttribute("username", username);
            session.setAttribute("password", user.getPassword());
            jsonResult = ResultFactory.buildSuccessJsonResult();
            Cookie tokenCookie = CookieUtil.buildCookie("accessToken", token);
            Cookie uidCookie = CookieUtil.buildCookie("uid", String.valueOf(user.getUid()));
            response.addCookie(tokenCookie);
            response.addCookie(uidCookie);

        } else if (state == JsonResultStatus.USERNAME_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_WRONG_DESC);
        } else if (state == JsonResultStatus.PASSWORD_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_WRONG_DESC);
        }

        return jsonResult;
    }

    @PostMapping("/register")
    @ResponseBody
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        Integer state = loginService.handleRegister(
                username,
                password,
                phoneNumber,
                verificationCode
        );
        if (state != null && state.equals(JsonResultStatus.SUCCESS)) {
            User newUser = new User();
            newUser.setUid(UidGenerator.getUid());
            newUser.setUsername(username);
            newUser.setPhone(phoneNumber);
            newUser.setSalt(SaltGenerator.getSalt());
            //存入摘要
            newUser.setPassword(DigestGenerator.getDigest(password, newUser.getSalt()));
            newUser.setRegisterTime(new Timestamp(System.currentTimeMillis()));

            loginService.addOne(newUser);
            return ResultFactory.buildSuccessJsonResult();
        } else {
            JsonResult jsonResult = ResultFactory.buildFailJsonResult(state, null);
            if (state != null && state.equals(JsonResultStatus.PHONE_NUMBER_EXISTED)) {
                jsonResult.setMessage(JsonResultStatus.PHONE_NUMBER_EXISTED_DESC);
            } else if (state != null && state.equals(JsonResultStatus.USERNAME_IS_EXITED)) {
                jsonResult.setMessage(JsonResultStatus.USERNAME_IS_EXITED_DESC);
            } else if (state != null && state.equals(JsonResultStatus.VERIFICATIONS_IS_WRONG)) {
                jsonResult.setMessage(JsonResultStatus.VERIFICATIONS_IS_WRONG_DESC);
            }

            return jsonResult;
        }
    }

    @PostMapping("/update_password")
    @ResponseBody
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        String password = jsonObject.getString("password");
        String passwordAgain = jsonObject.getString("passwordAgain");

        Integer state = loginService.handleUpdateUserPassword(
                username,
                password,
                phoneNumber,
                verificationCode,
                passwordAgain
        );
        if (state != null && state.equals(JsonResultStatus.SUCCESS)) {
            return ResultFactory.buildSuccessJsonResult();
        } else if (state != null && state.equals(JsonResultStatus.NOT_FOUND)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.NOT_FOUND, JsonResultStatus.NOT_FOUND_DESC);
        } else if (state != null && state.equals(JsonResultStatus.PHONE_NUMBER_IS_WRONG)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONE_NUMBER_IS_WRONG, JsonResultStatus.PHONE_NUMBER_IS_WRONG_DESC);
        } else if (state != null && state.equals(JsonResultStatus.VERIFICATIONS_IS_WRONG)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONS_IS_WRONG, JsonResultStatus.VERIFICATIONS_IS_WRONG_DESC);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UPDATE_PASSWORD_FAILED, JsonResultStatus.UPDATE_PASSWORD_FAILED_DESC);
        }
    }

    @PostMapping("/getvfcode")
    @ResponseBody
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = loginService.getVerificationCode(phoneNumber);
        if (verificationCode != null) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONS_GET_FAILED, JsonResultStatus.VERIFICATIONS_GET_FAILED_DESC);
        }
    }
}
