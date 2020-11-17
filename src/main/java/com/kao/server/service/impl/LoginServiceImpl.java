package com.kao.server.service.impl;

import com.kao.server.entity.User;
import com.kao.server.mapper.LoginMapper;
import com.kao.server.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginMapper loginMapper;

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
}
