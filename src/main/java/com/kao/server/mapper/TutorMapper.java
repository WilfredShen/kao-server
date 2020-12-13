package com.kao.server.mapper;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;

import java.util.Date;
import java.util.List;

/**
 * @author 全鸿润
 */
public interface TutorMapper {

    /**
     * 获取老师信息
     *
     * @param uid 用户id
     * @return 教师信息
     */
    TutorMessage findTutorById(int uid) throws Exception;

    /**
     * 修改教师信息
     *
     * @param msg 修改的教师信息
     * @param uid 用户id
     * @return 影响的行数
     */
    Integer updateTutorMessage(UpdatedTutorMessage msg, int uid) throws Exception;

    /**
     * 教师查询可见学生信息
     *
     * @param beginDate     起始日期
     * @param endDate       截止日期
     * @param collegeLevel  学校层次
     * @param major         专业
     * @param expectedMajor 预取专业
     * @return 学生信息列表
     */
    List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor) throws Exception;
}
