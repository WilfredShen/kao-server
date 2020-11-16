package com.kao.server.mapper;

import com.kao.server.entity.User;

public interface LoginMapper {

    public User findUserByUsername(String username);
    public User findUserNameByUsername(String username);
    public String findPhoneNumberByPhoneNumber(String phoneNumber);
    public Integer addOne(User user);
    public Integer updatePassword(String username,String newPassword);
}
