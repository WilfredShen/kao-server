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
     * @throws Exception 数据库操作异常
     */
    User findUserByUserId(int userId) throws Exception;

    /**
     * 修改手机号
     *
     * @param uid         用户id
     * @param phoneNumber 手机号
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    Integer updatePhone(int uid, String phoneNumber) throws Exception;

    /**
     * 修改邮箱
     *
     * @param uid   用户id
     * @param email 邮箱地址
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    Integer updateEmail(int uid, String email) throws Exception;

    /**
     * 获取未注册用户信息
     *
     * @param uid 用户id
     * @return 用户信息
     * @throws Exception 数据库操作异常
     */
    UserMessage getNotVerifiedUserMessageById(int uid) throws Exception;

    /**
     * 获取学生用户信息
     *
     * @param uid 用户id
     * @return 学生用户信息
     * @throws Exception 数据库操作异常
     */
    UserMessage getStudentUserMessageById(int uid) throws Exception;

    /**
     * 获取教师用户信息
     *
     * @param uid 用户id
     * @return 教师用户信息
     * @throws Exception 数据库操作异常
     */
    UserMessage getTutorUserMessageById(int uid) throws Exception;


}
