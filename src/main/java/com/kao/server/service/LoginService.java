package com.kao.server.service;

import com.kao.server.entity.User;
import com.kao.server.util.json.JsonResult;

/**
 * @author 全鸿润
 */
public interface LoginService {
    /**
     * 根据用户名获取用户对象
     *
     * @param username
     * @return User
     */
    public User findUserByUsername(String username);

    /**
     * 根据获取用户名,用于验证用户名是否已经存在
     *
     * @param username
     * @return User
     */
    public User findUserNameByUsername(String username);

    /**
     * 添加一个新用户
     *
     * @param user
     * @return 添加行数
     */
    public Integer addOne(User user);

    /**
     * 修改密码
     *
     * @param username
     * @param newPassword
     * @param phoneNumber
     * @param verificationCode
     * @param passwordAgain
     * @return 修改行数
     */
    public Integer updatePassword(String username, String newPassword, String phoneNumber, String verificationCode, String passwordAgain);

    /**
     * 手机号不能重复注册
     *
     * @param phoneNumber
     * @return
     */
    public String findPhoneNumberByPhoneNumber(String phoneNumber);

    /**
     * 处理登录逻辑功能
     *
     * @param username
     * @param password
     * @return json格式的处理结果
     */
    JsonResult handleLogin(String username, String password);

    /**
     * 处理注册逻辑功能
     *
     * @param username
     * @param password
     * @param phoneNumber
     * @param verificationCode
     * @return json格式的处理结果
     */
    JsonResult register(String username, String password, String phoneNumber, String verificationCode);

    /**
     * 处理修改密码逻辑功能
     *
     * @param username
     * @param password
     * @param phoneNumber
     * @param verificationCode
     * @param passwordAgain
     * @return json格式的处理结果
     */
    JsonResult updateUserPassword(String username, String password, String phoneNumber, String verificationCode, String passwordAgain);

    /**
     * 处理获取验证码逻辑
     *
     * @param phoneNumber
     * @return 验证码
     */
    JsonResult getVerificationCode(String phoneNumber);
}
