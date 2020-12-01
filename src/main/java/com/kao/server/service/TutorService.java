package com.kao.server.service;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;

import java.util.Date;
import java.util.List;

/**
 * @author 全鸿润
 */
public interface TutorService {

    /**
     * 通过用户id查询老师信息
     *
     * @param uid 用户id
     * @return 教师信息
     */
    TutorMessage findTutorById(int uid);

    /**
     * 通过用户id查询老师信息
     *
     * @param uid 用户id
     * @return 教师信息
     */
    TutorMessage getTutorMsg(int uid);

    /**
     * 修改教师信息
     *
     * @param message 修改的教师信息
     * @param uid     用户id
     * @return 影响的行数
     */
    Integer updateTutorMsg(UpdatedTutorMessage message, int uid);

    /**
     * 教师查询学生信息
     *
     * @param beginDate     起始时间
     * @param endDate       截止时间
     * @param collegeLevel  院校层次
     * @param major         专业
     * @param expectedMajor 预期专业
     * @return 学生信息列表
     */
    List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor);

}
