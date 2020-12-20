package com.kao.server.service.impl;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;
import com.kao.server.mapper.StudentMapper;
import com.kao.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public StudentMessage getStuMsg(int uid) {
        try {
            return studentMapper.findStudentById(uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateStudentMsg(UpdatedStudentMessage studentMessage, int uid) {
        try {
            return studentMapper.updateStudentMsg(studentMessage, uid);
        } catch (Exception e) {
            return null;
        }
    }
}
