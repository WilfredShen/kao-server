package com.kao.server.service.impl;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserByUserId(int userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public UserMessage getNotVerifiedUserMessageById(int uid) {
        return userMapper.getNotVerifiedUserMessageById(uid);
    }

    @Override
    public UserMessage getStudentUserMessageById(int uid) {
        return userMapper.getStudentUserMessageById(uid);
    }

    @Override
    public UserMessage getTutorUserMessageById(int uid) {
        return userMapper.getTutorUserMessageById(uid);
    }

    @Override
    public Integer updateUserMsg(String phoneNumber, String email, String accountType, int uid) {

        int state = 1;
        if (phoneNumber != null) {
            if (userMapper.updatePhone(uid, phoneNumber) != 1) {
                state = 0;
            }
        }
        if (email != null) {
            if (userMapper.updateEmail(uid, email) != 1) {
                state = 0;
            }
        }
        if (accountType != null) {
            if (userMapper.updateAccountType(uid, accountType) != 1) {
                state = 0;
            }
        }

        return state;
    }
}
