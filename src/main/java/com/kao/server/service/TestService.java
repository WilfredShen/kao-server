package com.kao.server.service;

import com.kao.server.entity.UserTest;

/**
 * @author 全鸿润
 */
public interface TestService {

    UserTest findUserById(Integer id);

    Integer addNewUser(UserTest test);
}
