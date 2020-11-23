package com.kao.server.mapper;

import com.kao.server.entity.User;

public interface UserMapper {

    public User findUserByUserId(int userId);
    public Integer updatePhone(int uid,String phoneNumber);
    public Integer updateEmail(int uid ,String email);
    public Integer updateAccountType(int uid,String accountType);

}
