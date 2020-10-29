package com.kao.server.mapper;

import com.kao.server.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    //注册：添加一个实例
    public int insertNewUser(User user);
    //验证注册:账户名存在不能注册
    public String selectUsernameByname(String userName);
    //登录:根据输入的账号寻找用户对象
    public User selectPasswordByUserName(String userName);
    //忘记密码
    public Integer updatePassword(String username,String password);
}
