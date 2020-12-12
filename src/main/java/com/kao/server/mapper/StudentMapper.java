package com.kao.server.mapper;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;

/**
 * @author 全鸿润
 */
public interface StudentMapper {

    /**
     * 通过uid获取学生信息
     *
     * @param uid 用户id
     * @return 学生信息
     */
    StudentMessage findStudentById(int uid)throws Exception;

    /**
     * 修改学生信息
     *
     * @param msg 修改的学生信息
     * @param uid 用户id
     * @return 影响的行数
     */
    Integer updateStudentMsg(UpdatedStudentMessage msg, int uid)throws Exception;
}
