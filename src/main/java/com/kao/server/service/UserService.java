package com.kao.server.service;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface UserService {
    /**
     * 根据uid查询用户
     *
     * @param userId 用户id
     * @return User实例
     */
    User findUserByUserId(int userId);

    /**
     * 修改用户信息
     *
     * @param phoneNumber 手机号
     * @param email       邮箱
     * @param uid         用户id
     * @return 影响的行数
     */
    Integer updateUserMsg(String phoneNumber, String email,int uid);

    /**
     * 获取未注册用户信息
     *
     * @param uid 用户id
     * @return 未注册用户信息
     */
    UserMessage getNotVerifiedUserMessageById(int uid);

    /**
     * 获取学生用户信息
     *
     * @param uid 用户id
     * @return 学生用户信息
     */
    UserMessage getStudentUserMessageById(int uid);

    /**
     * 获取老师用户信息
     *
     * @param uid 用户id
     * @return 老师用户信息
     */
    UserMessage getTutorUserMessageById(int uid);


}
