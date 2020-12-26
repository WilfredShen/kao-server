package com.kao.server.service.impl;

import com.kao.server.entity.UserTest;
import com.kao.server.mapper.TestMapper;
import com.kao.server.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Override
    public UserTest findUserById(Integer id) {
        try {
            return testMapper.findUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addNewUser(UserTest test) {
        try {
            return testMapper.addNewUser(test);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
