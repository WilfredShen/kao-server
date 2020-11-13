package com.kao.server.service.InterFaces;

import com.kao.server.entity.User;

public interface RegisterService extends SmsService {
    //注册：添加一个实例
    public int insertNewUser(User user);
    //验证注册:账户名存在不能注册
    public String selectUsernameByname(String userName);
}
