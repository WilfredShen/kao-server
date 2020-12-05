package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import com.kao.server.service.SmsService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.DigestGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private SmsService smsService;

    @Override
    public User findUserByUsername(String username) {
        return loginMapper.findUserByUsername(username);
    }

    @Override
    public User findUserNameByUsername(String username) {
        return loginMapper.findUserNameByUsername(username);
    }

    @Override
    public String findPhoneNumberByPhoneNumber(String phoneNumber) {
        return loginMapper.findPhoneNumberByPhoneNumber(phoneNumber);
    }

    @Override
    public Integer addOne(User user) {
        return loginMapper.addOne(user);
    }

    @Override
    public int handleLogin(User user, String username, String password) {

        return LoginChecker.checkLogin(user, username, password);
    }

    @Override
    public int handleRegister(String username, String password, String phoneNumber, String verificationCode) {
        User user = this.findUserNameByUsername(username);
        //验证码先写死成666666
        String verification = "666666";
        if (user == null) {
            if ((loginMapper.findPhoneNumberByPhoneNumber(phoneNumber) != null)) {
                return JsonResultStatus.PHONE_NUMBER_EXISTED;
            } else if (!verification.equals(verificationCode)) {
                return JsonResultStatus.VERIFICATIONS_IS_WRONG;
            } else {
                return JsonResultStatus.SUCCESS;
            }

        } else {
            return JsonResultStatus.USERNAME_IS_EXITED;
        }
    }

    @Override
    public int handleUpdateUserPassword(String username, String password, String phoneNumber,
                                        String verificationCode, String passwordAgain) {
        User user = loginMapper.findUserByUsername(username);
        String digest = DigestGenerator.getDigest(password, user.getSalt());
        int raws = loginMapper.updatePassword(username, digest);
        if (raws == 1) {
            return JsonResultStatus.SUCCESS;
        } else {
            return JsonResultStatus.FAIL;
        }
    }

    @Override
    public String getVerificationCode(String phoneNumber) {

        return smsService.getVerificationCode(phoneNumber);
    }

}
