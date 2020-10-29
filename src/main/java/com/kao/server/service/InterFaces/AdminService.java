package com.kao.server.service.InterFaces;

import com.kao.server.entity.Admin;

public interface AdminService {

    //登录:根据输入的账号寻找用户对象
    public Admin selectAdmindByUserName(String userName);
}
