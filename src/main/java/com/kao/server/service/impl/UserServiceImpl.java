package com.kao.server.service.impl;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.UserService;
import com.kao.server.util.accounttype.AccountTypeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User findUserByUserId(int userId) {
        User user = null;
        try {
            String key = "findUserByUserId" + userId;
            System.err.println("findUserByUserId: " + userId);
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                user = (User) redisTemplate.opsForValue().get(key);
            } else {
                user = userMapper.findUserByUserId(userId);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserMessage getNotVerifiedUserMessageById(Integer uid) {
        try {
            String key = String.valueOf(uid);
            Boolean flag = (redisTemplate.hasKey(key));
            if (flag != null && flag) {
                System.err.println("getNotVerifiedUserMessageById: " + true);
                return (UserMessage) redisTemplate.opsForValue().get(key);
            } else {
                System.err.println("getNotVerifiedUserMessageById: " + uid);
                UserMessage userMessage = userMapper.getNotVerifiedUserMessageById(uid);
                redisTemplate.opsForValue().set(key, userMessage);
                return userMessage;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserMessage getStudentUserMessageById(Integer uid) {
        try {
            String key = String.valueOf(uid);
            UserMessage userMessage;
            Boolean flag = (redisTemplate.hasKey(key));
            if (flag != null && flag) {
                System.err.println("getStudentUserMessageById: " + true);
                userMessage = (UserMessage) redisTemplate.opsForValue().get(key);
                return userMessage;
            } else {
                System.err.println("getStudentUserMessageById: " + uid);
                userMessage = userMapper.getStudentUserMessageById(uid);
                redisTemplate.opsForValue().set(key, userMessage);
                return userMessage;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserMessage getTutorUserMessageById(Integer uid) {
        try {
            String key = String.valueOf(uid);
            Boolean flag = (redisTemplate.hasKey(key));
            if (flag != null && flag) {
                System.err.println("getTutorUserMessageById: " + true);
                return (UserMessage) redisTemplate.opsForValue().get(key);
            } else {
                System.err.println("getTutorUserMessageById: " + uid);
                UserMessage userMessage = userMapper.getTutorUserMessageById(uid);
                redisTemplate.opsForValue().set(key, userMessage);
                return userMessage;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateUserMsg(String phoneNumber, String email, int uid) {

        int state = 1;
        if (phoneNumber != null) {
            try {
                if (userMapper.updatePhone(uid, phoneNumber) != 1) {
                    state = 0;
                }
            } catch (Exception e) {
                return null;
            }
        }
        if (email != null) {
            try {
                if (userMapper.updateEmail(uid, email) != 1) {
                    state = 0;
                }
            } catch (Exception e) {
                return null;
            }
        }
        //更新缓存
        if (state == 1) {
            System.err.println("updateUserMessage: 更新缓存");
            try {
                String key1 = String.valueOf(uid);
                String key2 = "findUserByUserId" + uid;
                User user = userMapper.findUserByUserId(uid);
                redisTemplate.opsForValue().set(key2, user);
                if (user.getAccountType().equals(AccountTypeConstant.getStudentType())) {
                    UserMessage userMessage = userMapper.getStudentUserMessageById(uid);
                    System.err.println("update" + userMessage);
                    redisTemplate.opsForValue().set(key1, userMessage);
                    System.err.println("测试: " + redisTemplate.opsForValue().get(String.valueOf(uid)));
                } else if (user.getAccountType().equals(AccountTypeConstant.getTeacherType())) {
                    redisTemplate.opsForValue().set(key1, userMapper.getTutorUserMessageById(uid));
                } else {
                    redisTemplate.opsForValue().set(key1, userMapper.getNotVerifiedUserMessageById(uid));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return state;
    }
}
