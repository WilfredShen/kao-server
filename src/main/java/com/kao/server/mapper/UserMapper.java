package com.kao.server.mapper;

import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface UserMapper {

    /**
     * 通过用户id获取用户信息
     *
     * @param userId 用户id
     * @return 用户对象
     */
    User findUserByUserId(int userId);

    /**
     * 修改手机号
     *
     * @param uid         用户id
     * @param phoneNumber 手机号
     * @return 影响的行数
     */
    Integer updatePhone(int uid, String phoneNumber);

    /**
     * 修改邮箱
     *
     * @param uid   用户id
     * @param email 邮箱地址
     * @return 影响的行数
     */
    Integer updateEmail(int uid, String email);

    /**
     * 修改用户类型
     *
     * @param uid         用户id
     * @param accountType 用户类型
     * @return 影响的行数
     */
    Integer updateAccountType(int uid, String accountType);

    /**
     * 获取未注册用户信息
     *
     * @param uid 用户id
     * @return 用户信息
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
     * 获取教师用户信息
     *
     * @param uid 用户id
     * @return 教师用户信息
     */
    UserMessage getTutorUserMessageById(int uid);
}
