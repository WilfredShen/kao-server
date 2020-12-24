package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import com.kao.server.service.SmsService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.DigestGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

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
    @Value("${redis.key.prefix.authCode}")
    private String authCode;
    @Value("${redis.key.expire.authCode}")
    private Long expireTime;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#username")
    public User findUserByUsername(String username) {

        try {
            return loginMapper.findUserByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#username")
    public User findUserNameByUsername(String username) {
        try {
            return loginMapper.findUserNameByUsername(username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer addOne(User user) {

        try {
            Integer raw = loginMapper.addOne(user);
            if (raw != null && raw == 1) {
                redisTemplate.opsForValue().set(user.getUsername(), user);
                System.err.println("addOne:" + redisTemplate.opsForValue().get(user.getUsername()));
            }
            return raw;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer handleLogin(String username, String password) {

        try {
            User user;
            Boolean flag = redisTemplate.hasKey(username);
            if (flag != null && flag) {
                user = (User) redisTemplate.opsForValue().get(username);
            } else {
                user = loginMapper.findUserByUsername(username);
                if (user != null) {
                    redisTemplate.opsForValue().set(username, user);
                }
            }
            return LoginChecker.checkLogin(user, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Integer handleRegister(String username, String password, String phoneNumber, String verificationCode) {

        String verification = (String) redisTemplate.opsForValue().get(phoneNumber);
        System.err.println(verification);
        Boolean flag = redisTemplate.hasKey(username);
        if (flag != null && flag) {
            return JsonResultStatus.USERNAME_IS_EXITED;
        } else {
            User user;
            try {
                user = loginMapper.findUserNameByUsername(username);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            if (user == null) {
                try {
                    if ((loginMapper.findPhoneNumberByPhoneNumber(phoneNumber) != null)) {
                        return JsonResultStatus.PHONE_NUMBER_EXISTED;
                    } else if (!verificationCode.equals(verification)) {
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
        String verification = (String) redisTemplate.opsForValue().get(phoneNumber);
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
            if (raws == 1) {
                User newUser = loginMapper.findUserByUsername(username);
                redisTemplate.delete(username);
                redisTemplate.opsForValue().set(username, user);
                return JsonResultStatus.SUCCESS;
            } else {
                return JsonResultStatus.FAIL;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#phoneNumber")
    public String getVerificationCode(String phoneNumber) {
        String verificationCode = "666666";
        redisTemplate.expire(phoneNumber, expireTime, TimeUnit.SECONDS);
        System.err.println(redisTemplate.opsForValue().get(authCode + phoneNumber));
        return verificationCode;
    }

}
