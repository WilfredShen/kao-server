package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.mapper.VerificationMapper;
import com.kao.server.service.VerificationService;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.verification.VerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 沈伟峰
 */
@Service
@PropertySource(value = {"classpath:application.yml"})
public class VerificationServiceImpl implements VerificationService {

    @Autowired
    private VerificationMapper verificationMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    private String key1;
    private String key2;

    @Override
    public Integer realAuth(Integer uid, String identity, String name) {
        int status;
        Map<Object, Object> map = VerificationUtil.identityAuth(identity, name);
        key1 = String.valueOf(uid);
        key2 = "findUserByUserId" + uid;
        if (map != null && (boolean) map.get("isOK")) {
            try {
                String idCardNo = (String) map.get("identity");
                int sex = "M".equals((String) map.get("sex")) ? 0 : 1;
                Date birthday = (Date) map.get("birthday");
                verificationMapper.realAuth(uid, idCardNo, name, sex, birthday);
                redisTemplate.delete(key1);
                redisTemplate.delete(key2);
                redisTemplate.opsForValue().set(key1, userMapper.getVerifiedUserMessageById(uid));
                redisTemplate.opsForValue().set(key2, userMapper.findUserByUserId(uid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(key2, expireTime, TimeUnit.MINUTES);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.REAL_AUTH_FAILED;
        }
        return status;
    }

    @Override
    public Integer studentAuth(Integer uid, String cid, String sid) {
        int status;
        User user;
        key1 = String.valueOf(uid);
        key2 = "findUserByUserId" + uid;
        try {
            user = userMapper.findUserByUserId(uid);
        } catch (Exception e) {
            return null;
        }
        if (user.getAccountType() != null) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        try {
            verificationMapper.studentAuth(uid, cid, sid);
        } catch (Exception e) {
            e.printStackTrace();
            status = JsonResultStatus.FAIL;
            return status;
        }
        boolean flag = VerificationUtil.studentAuth(cid, sid);
        if (flag) {
            try {
                verificationMapper.studentAuthVerified(uid);
                redisTemplate.delete(key1);
                redisTemplate.delete(key2);
                redisTemplate.opsForValue().set(key1, userMapper.getStudentUserMessageById(uid));
                redisTemplate.opsForValue().set(key2, userMapper.findUserByUserId(uid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(key2, expireTime, TimeUnit.MINUTES);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.STUDENT_AUTH_FAILED;
        }
        return status;
    }

    @Override
    public Integer tutorAuth(Integer uid, String cid, String tid) {
        int status;
        User user;
        key1 = String.valueOf(uid);
        key2 = "findUserByUserId" + uid;
        try {
            user = userMapper.findUserByUserId(uid);
        } catch (Exception e) {
            return null;
        }
        if (user.getAccountType() != null) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        try {
            verificationMapper.tutorAuth(uid, cid, tid);
        } catch (Exception e) {
            status = JsonResultStatus.FAIL;
            return status;
        }
        boolean flag = VerificationUtil.tutorAuth(cid, tid);
        if (flag) {
            try {
                verificationMapper.tutorAuthVerified(uid);
                redisTemplate.delete(key1);
                redisTemplate.delete(key2);
                redisTemplate.opsForValue().set(key1, userMapper.getTutorUserMessageById(uid));
                redisTemplate.opsForValue().set(key2, userMapper.findUserByUserId(uid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(key2, expireTime, TimeUnit.MINUTES);
                status = JsonResultStatus.SUCCESS;
            } catch (Exception e) {
                status = JsonResultStatus.FAIL;
            }
        } else {
            status = JsonResultStatus.TUTOR_AUTH_FAILED;
        }
        return status;
    }
}
