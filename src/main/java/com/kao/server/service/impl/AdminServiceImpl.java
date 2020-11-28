package com.kao.server.service.impl;

import com.kao.server.entity.Admin;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.service.AdminService;
import com.kao.server.util.checker.LoginChecker;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findUserByUsername(String username) {
        return adminMapper.findUserByUsername(username);
    }

    public JsonResult handleLogin(String username,String password){

        Admin admin = adminMapper.findUserByUsername(username);
        return LoginChecker.checkLogin(admin,username,password);
    }

}
