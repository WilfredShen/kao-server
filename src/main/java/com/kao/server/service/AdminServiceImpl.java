package com.kao.server.service;

import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.service.InterFaces.AdminService;
import com.kao.server.service.InterFaces.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl implements AdminService, LoginService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin selectAdmindByUserName(String userName) {
        return adminMapper.selectAdminByUserName(userName);
    }

    @Override
    public Admin selectPasswordByUserName(String userName) {
        return adminMapper.selectAdminByUserName(userName);
    }

    @Override
    public int updatePassword(String userName, String passwordAgain) {
        return 0;
    }
}
