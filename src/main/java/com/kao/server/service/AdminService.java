package com.kao.server.service;

import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.util.json.JsonResult;

public interface AdminService {

    /**
     * 通过用户名查找管理员
     * @param username
     * @return Admin
     */
    public Admin findUserByUsername(String username);
}
