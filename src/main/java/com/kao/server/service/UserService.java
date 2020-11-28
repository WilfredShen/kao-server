package com.kao.server.service;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface UserService {
    /**
     *
     * @param userId
     * @return
     */
    public User findUserByUserId(int userId);

    public Integer updatePhone(int uid, String phoneNumber);

    public Integer updateEmail(int uid, String email);

    public Integer updateAccountType(int uid, String accountType);

    public UserMessage getNotVerifiedUserMessageById(int uid);

    public UserMessage getStudentUserMessageById(int uid);
}
