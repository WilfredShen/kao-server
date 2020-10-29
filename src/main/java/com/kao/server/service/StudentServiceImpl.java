package com.kao.server.service;

import com.kao.server.mapper.StudentMapper;
import com.kao.server.service.InterFaces.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentServiceImpl extends BaseServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
}
