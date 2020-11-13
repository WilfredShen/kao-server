package com.kao.server.service.InterFaces;

import com.kao.server.entity.User;

public interface LoginService {
    //登录:根据输入的账号寻找用户对象
    public Object selectPasswordByUserName(String userName);
    //忘记密码,修改密码
    public int updatePassword(String userName,String passwordAgain);
}
