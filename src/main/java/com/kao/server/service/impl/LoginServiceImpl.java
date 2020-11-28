package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import com.kao.server.service.SmsService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.checker.RegisterChecker;
import com.kao.server.util.checker.UpdatePasswordChecker;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.login.GetVerificationCodeChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;
    @Autowired
    SmsService smsService;

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
    public Integer updatePassword(String username, String newPassword, String phoneNumber, String verificationCode, String passwordAgain) {
        return loginMapper.updatePassword(username, newPassword);
    }

    @Override
    public String findPhoneNumberByPhoneNumber(String phoneNumber) {
        return loginMapper.findPhoneNumberByPhoneNumber(phoneNumber);
    }

    @Override
    public JsonResult handleLogin(String username, String password) {

        User user = this.findUserByUsername(username);
        return LoginChecker.checkLogin(user, username, password);
    }

    @Override
    public JsonResult register(String username, String password, String phoneNumber, String verificationCode) {

        User user = this.findUserNameByUsername(username);
        return RegisterChecker.checkRegister(user, username, password, phoneNumber, verificationCode, this);
    }

    @Override
    public JsonResult updateUserPassword(String username, String password, String phoneNumber, String verificationCode, String passwordAgain) {

        return UpdatePasswordChecker.checkUpdatePassword(username, password, passwordAgain, phoneNumber, verificationCode, this);
    }

    @Override
    public JsonResult getVerificationCode(String phoneNumber) {

        return GetVerificationCodeChecker.checkGetVerificationCode(phoneNumber, smsService);
    }

}
