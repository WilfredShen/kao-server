package com.kao.server.service;

import com.kao.server.entity.User;
import com.kao.server.mapper.QueryMapper;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.InterFaces.BaseService;
import com.kao.server.util.SmsUtil.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    QueryMapper queryMapper;

    @Override
    public int updatePassword(String userName,String passwordAgain) {

        return userMapper.updatePassword(userName,passwordAgain);
    }

    @Override
    public int insertNewUser(User user) {
        return userMapper.insertNewUser(user);
    }

    @Override
    public String getverifiedCode(String phoneNumber) {

        return SmsUtil.getverifiedCode(phoneNumber);
    }

    @Override
    public String selectUsernameByname(String userName) {
        return userMapper.selectUsernameByname(userName);
    }

    @Override
    public User selectPasswordByUserName(String userName) {
        return userMapper.selectPasswordByUserName(userName);
    }
}
