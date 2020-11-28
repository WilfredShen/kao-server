package com.kao.server.service;

import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface LoginService {
    /**
     *
     * @param username
     * @return
     */
    public User findUserByUsername(String username);

    public User findUserNameByUsername(String username);

    public Integer addOne(User user);

    public Integer updatePassword(String username, String newPassword, String phoneNumber, String verificationCode, String passwordAgain);

    public String findPhoneNumberByPhoneNumber(String phoneNumber);
}
