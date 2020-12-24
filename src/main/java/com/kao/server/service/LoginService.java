package com.kao.server.service;

import com.kao.server.entity.User;

/**
 * @author 全鸿润
 */
public interface LoginService {
    /**
     * 根据用户名获取用户对象
     *
     * @param username 用户名
     * @return User
     */
    User findUserByUsername(String username);

    /**
     * 根据获取用户名,用于验证用户名是否已经存在
     *
     * @param username 用户名
     * @return User
     */
    User findUserNameByUsername(String username);

    /**
     * 添加一个新用户
     *
     * @param user 要添加的用户对象
     * @return 添加行数
     */
    Integer addOne(User user);

    /**
     * 处理登录逻辑功能
     *
     * @param username 用户名
     * @param password 密码
     * @return json格式的处理结果
     */
    Integer handleLogin(String username, String password);

    /**
     * 处理注册逻辑功能
     *
     * @param username         用户名
     * @param password         密码
     * @param phoneNumber      手机号
     * @param verificationCode 验证码
     * @return 状态码
     */
    Integer handleRegister(String username, String password, String phoneNumber, String verificationCode);

    /**
     * 处理修改密码逻辑功能
     *
     * @param username         用户名
     * @param password         密码
     * @param phoneNumber      手机号
     * @param verificationCode 验证码
     * @param passwordAgain    新密码
     * @return 状态码
     */
    Integer handleUpdateUserPassword(String username, String password, String phoneNumber, String verificationCode, String passwordAgain);

    /**
     * 处理获取验证码逻辑
     *
     * @param phoneNumber 手机号
     * @return 验证码
     */
    String getVerificationCode(String phoneNumber);
}
