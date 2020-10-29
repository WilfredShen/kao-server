package com.kao.server.mapper;

import com.kao.server.entity.Admin;

public interface AdminMapper {

    //登录:根据输入的账号寻找用户对象
    public Admin selectAdminByUserName(String userName);
}
