package com.kao.server.mapper;

import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface LoginMapper {

    /**
     * 通过用户名查找用户
     *
     * @param username 用户名
     * @return 用户对象
     * @throws Exception 数据库操作异常
     */
    User findUserByUsername(String username) throws Exception;

    /**
     * 验证用户名是否存在
     *
     * @param username 用户名
     * @return 用户名
     * @throws Exception 数据库操作异常
     */
    User findUserNameByUsername(String username) throws Exception;

    /**
     * None
     *
     * @param phoneNumber 手机号码
     * @return 手机号
     * @throws Exception 数据库操作异常
     */
    String findPhoneNumberByPhoneNumber(String phoneNumber) throws Exception;

    /**
     * 添加用户
     *
     * @param user 注册的用户对象
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    Integer addOne(User user) throws Exception;

    /**
     * 修改密码
     *
     * @param username    用户名
     * @param newPassword 修改后的密码
     * @return 影响的行数
     * @throws Exception 数据库操作异常
     */
    Integer updatePassword(String username, String newPassword) throws Exception;
}
