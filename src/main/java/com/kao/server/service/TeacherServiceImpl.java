package com.kao.server.service;

import com.kao.server.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TeacherServiceImpl extends BaseServiceImpl {

    @Autowired
    TeacherMapper teacherMapper;
}
