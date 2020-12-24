package com.kao.server.service.impl;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+userId")
    public User findUserByUserId(int userId) {
        try {
            return userMapper.findUserByUserId(userId);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#uid")
    public UserMessage getNotVerifiedUserMessageById(int uid) {
        try {
            return userMapper.getNotVerifiedUserMessageById(uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#uid")
    public UserMessage getStudentUserMessageById(int uid) {
        try {
            return userMapper.getStudentUserMessageById(uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#uid")
    public UserMessage getTutorUserMessageById(int uid) {
        try {
            return userMapper.getTutorUserMessageById(uid);
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

        return state;
    }
}
