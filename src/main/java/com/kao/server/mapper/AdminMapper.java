package com.kao.server.mapper;

import com.kao.server.entity.Admin;

public interface AdminMapper {
    /**
     * 通过用户名查找管理员
     * @param username
     * @return Admin
     */
    public Admin findUserByUsername(String username);
}
