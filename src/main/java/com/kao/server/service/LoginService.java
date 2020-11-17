package com.kao.server.service;

import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface LoginService {

    public User findUserByUsername(String username);

    public User findUserNameByUsername(String username);

    public Integer addOne(User user);

    public Integer updatePassword(String username, String newPassword);

    public String findPhoneNumberByPhoneNumber(String phoneNumber);
}