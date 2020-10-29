package com.kao.server.service;

import com.kao.server.entity.Admin;
import com.kao.server.mapper.AdminMapper;
import com.kao.server.service.InterFaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminServiceImpl extends BaseServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin selectAdmindByUserName(String userName) {
        return adminMapper.selectAdminByUserName(userName);
    }
}
