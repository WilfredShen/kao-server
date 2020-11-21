package com.kao.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.checker.RegisterChecker;
import com.kao.server.util.checker.UpdatePasswordChecker;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.GetVerificationCodeChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 全鸿润
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    SmsServiceImpl smsService;

    @Override
    public User findUserByUsername(String username) {
        return loginMapper.findUserByUsername(username);
    }

    @Override
    public User findUserNameByUsername(String username) {
        return loginMapper.findUserNameByUsername(username);
    }

    @Override
    public Integer addOne(User user) {
        return loginMapper.addOne(user);
    }

    @Override
    public Integer updatePassword(String username, String newPassword) {
        return loginMapper.updatePassword(username, newPassword);
    }

    @Override
    public String findPhoneNumberByPhoneNumber(String phoneNumber) {
        return loginMapper.findPhoneNumberByPhoneNumber(phoneNumber);
    }

    public JsonResult login(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        HttpSession session = request.getSession();
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        return LoginChecker.checkLogin(username, password, this);
    }
    public JsonResult register(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");

        return RegisterChecker.checkRegister(username, password, phoneNumber, verificationCode, this);
    }
    public JsonResult updatePassword(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String username = jsonObject.getString("username");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String verificationCode = jsonObject.getString("verificationCode");
        String password = jsonObject.getString("password");
        String passwordAgain = jsonObject.getString("passwordAgain");

        return UpdatePasswordChecker.checkUpdatePassword(username, password, passwordAgain, phoneNumber, verificationCode, this);
    }

    public JsonResult getVerificationCode(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        String phoneNumber = jsonObject.getString("phoneNumber");
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);

        return GetVerificationCodeChecker.checkGetVerificationCode(phoneNumber, this, smsService);
    }

}
