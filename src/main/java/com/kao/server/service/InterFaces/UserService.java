package com.kao.server.service.InterFaces;

import com.kao.server.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends LoginService,RegisterService {

    //注册：添加一个实例
    @Override
    public int insertNewUser(User user);
    //验证注册:账户名存在不能注册
    @Override
    public String selectUsernameByname(String userName);
    //登录:根据输入的账号寻找用户对象
    @Override
    public User selectPasswordByUserName(String userName);
    //忘记密码,修改密码
    public int updatePassword(String userName,String passwordAgain);
}
