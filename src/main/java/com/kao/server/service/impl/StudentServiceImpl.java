package com.kao.server.service.impl;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;
import com.kao.server.mapper.StudentMapper;
import com.kao.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentMessage getStuMsg(int uid) {
        return studentMapper.findStudentById(uid);
    }

    @Override
    public Integer updateStudentMsg(UpdatedStudentMessage studentMessage, int uid) {
        return studentMapper.updateStudentMsg(studentMessage, uid);
    }
}
