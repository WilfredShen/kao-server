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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 登录
     *
     * @param jsonObject 包括用户名,密码
     * @param request    HttpServletResponse
     * @param response   HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JsonResult jsonResult = null;
        int state = loginService.handleLogin(username, password);
        if (state == JsonResultStatus.SUCCESS) {
            User user = (User) redisTemplate.opsForValue().get(username);
            String token = TokenGenerator.generateToken(
                    (user).getUsername(),
                    String.valueOf(user.getUid()),
                    (user).getPassword()
            );
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

    /**
     * 注册
     *
     * @param jsonObject 包括用户名，密码，手机号，验证码
     * @param request    HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/register")
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

            Integer row = loginService.addOne(newUser);
            if (row != null && row == 1) {
                return ResultFactory.buildSuccessJsonResult();
            } else {
                return ResultFactory.buildFailJsonResult();
            }
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

    /**
     * 修改密码
     *
     * @param jsonObject 包括用户名，密码，新密码，手机号，验证码
     * @param request    HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/update-password")
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
        System.err.println(state);
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

    /**
     * 获取验证码
     *
     * @param jsonObject 包括手机号
     * @return 封装的Json数据
     */
    @PostMapping("/getvfcode")
    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        System.err.println(phoneNumber);
        String verificationCode = loginService.getVerificationCode(phoneNumber);
        if (verificationCode != null) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.VERIFICATIONS_GET_FAILED, JsonResultStatus.VERIFICATIONS_GET_FAILED_DESC);
        }
    }
}
