package com.kao.server.mapper;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;

public interface UserMapper {

    public User findUserByUserId(int userId);

    public Integer updatePhone(int uid, String phoneNumber);

    public Integer updateEmail(int uid, String email);

    public Integer updateAccountType(int uid, String accountType);

    public UserMessage getNotVerifiedUserMessageById(int uid);

    public UserMessage getStudentUserMessageById(int uid);

    public UserMessage getTutorUserMessageById(int uid);
}
