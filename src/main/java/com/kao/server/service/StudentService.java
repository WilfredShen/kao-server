package com.kao.server.service;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;

/**
 * @author 全鸿润
 */
public interface StudentService {
    /**
     * 根据uid获取学生信息
     *
     * @param uid 用户id
     * @return 学生信息
     */
    StudentMessage getStuMsg(int uid);

    /**
     * 修改学生信息
     *
     * @param studentMessage 封装学生修改的信息
     * @param uid            用户id
     * @return 修改后的结果
     */
    Integer updateStudentMsg(UpdatedStudentMessage studentMessage, int uid);
}
