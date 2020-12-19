package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import com.kao.server.service.RedisService;
import com.kao.server.service.SmsService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.DigestGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private SmsService smsService;
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String authCode;
    @Value("${redis.key.expire.authCode}")
    private Long expireTime;


    @Override
    public User findUserByUsername(String username) {
        try {
            return loginMapper.findUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public User findUserNameByUsername(String username) {
        try {
            return loginMapper.findUserNameByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String findPhoneNumberByPhoneNumber(String phoneNumber) {
        try {
            return loginMapper.findPhoneNumberByPhoneNumber(phoneNumber);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer addOne(User user) {
        try {
            return loginMapper.addOne(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer handleLogin(User user, String username, String password) {

        return LoginChecker.checkLogin(user, username, password);
    }

    @Override
    public Integer handleRegister(String username, String password, String phoneNumber, String verificationCode) {
        User user = this.findUserNameByUsername(username);
        //验证码先写死成666666
        String verification = "666666";
        if (user == null) {
            try {
                if ((loginMapper.findPhoneNumberByPhoneNumber(phoneNumber) != null)) {
                    return JsonResultStatus.PHONE_NUMBER_EXISTED;
                } else if (!verification.equals(verificationCode)) {
                    return JsonResultStatus.VERIFICATIONS_IS_WRONG;
                } else {
                    return JsonResultStatus.SUCCESS;
                }
            } catch (Exception e) {
                return null;
            }

        } else {
            return JsonResultStatus.USERNAME_IS_EXITED;
        }
    }

    @Override
    public Integer handleUpdateUserPassword(String username, String password, String phoneNumber,
                                            String verificationCode, String passwordAgain) {
        User user;
        try {
            user = loginMapper.findUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
        String verification = redisService.get(authCode + phoneNumber);
        if (user == null) {
            return JsonResultStatus.NOT_FOUND;
        } else if (!user.getPhone().equals(phoneNumber)) {
            return JsonResultStatus.PHONE_NUMBER_IS_WRONG;
        } else if (!verificationCode.equals(verification)) {
            return JsonResultStatus.VERIFICATIONS_IS_WRONG;
        }
        String digest = DigestGenerator.getDigest(password, user.getSalt());
        int raws;
        try {
            raws = loginMapper.updatePassword(username, digest);
        } catch (Exception e) {
            return null;
        }
        if (raws == 1) {
            return JsonResultStatus.SUCCESS;
        } else {
            return JsonResultStatus.FAIL;
        }
    }

    @Override
    public String getVerificationCode(String phoneNumber) {
        String verificationCode = "666666";
        if (verificationCode != null) {
            redisService.set(authCode + phoneNumber, verificationCode);
            redisService.expire(authCode + phoneNumber, expireTime);
        }
        return verificationCode;
    }

}
