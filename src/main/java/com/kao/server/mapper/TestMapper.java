package com.kao.server.mapper;

import com.kao.server.entity.User;
import com.kao.server.entity.UserTest;

/**
 * @author 全鸿润
 */
public interface TestMapper {

    UserTest findUserById(Integer id) throws Exception;

    Integer addNewUser(UserTest test) throws Exception;
}
