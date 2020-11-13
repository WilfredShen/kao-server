package com.kao.server.service;

import com.kao.server.entity.User;
import com.kao.server.mapper.QueryMapper;
import com.kao.server.mapper.UserMapper;
import com.kao.server.service.InterFaces.BaseService;
import com.kao.server.util.SmsUtil.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService
{
    @Autowired
    QueryMapper queryMapper;
}
