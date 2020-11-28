package com.kao.server.service;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.util.json.JsonResult;

/**
 * @author 全鸿润
 */
public interface UserService {
    /**
     * 根据uid查询用户
     *
     * @param userId
     * @return User实例
     */
    public User findUserByUserId(int userId);

    public Integer updatePhone(int uid, String phoneNumber);

    public Integer updateEmail(int uid, String email);

    public Integer updateAccountType(int uid, String accountType);

    public UserMessage getNotVerifiedUserMessageById(int uid);

    public UserMessage getStudentUserMessageById(int uid);

    JsonResult getUserMessage(String id);

    JsonResult updateUserMsg(String phoneNumber, String email, String accountType, String uid);
}
