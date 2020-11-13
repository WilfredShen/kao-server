package com.kao.server.service;

import com.kao.server.entity.User;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.InterFaces.UserService;
import com.kao.server.util.SmsUtil.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int insertNewUser(User user) {
        return userMapper.insertNewUser(user);
    }

    @Override
    public String selectUsernameByname(String userName) {
        return userMapper.selectUsernameByname(userName);
    }

    @Override
    public User selectPasswordByUserName(String userName) {
        return userMapper.selectPasswordByUserName(userName);
    }

    @Override
    public int updatePassword(String userName, String passwordAgain) {

        return userMapper.updatePassword(userName, passwordAgain);
    }

    @Override
    public String getverifiedCode(String phoneNumber) {
        return SmsUtil.getverifiedCode(phoneNumber);
    }
}
