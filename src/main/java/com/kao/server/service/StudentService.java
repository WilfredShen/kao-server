package com.kao.server.service;

import com.kao.server.dto.StudentMessage;
import com.kao.server.util.json.JsonResult;

import java.sql.Date;

public interface StudentService {
    /**
     * 根据uid获取学生信息
     *
     * @param uid
     * @return 学生信息
     */
    public StudentMessage getStuMsg(int uid);

    /**
     * 修改手机号
     *
     * @param uid
     * @param phone
     * @return 修改行数
     */
    public Integer updatePhone(int uid, String phone);

    /**
     * 修改邮箱
     *
     * @param uid
     * @param email
     * @return 修改行数
     */
    public Integer updateEmail(int uid, String email);

    /**
     * 修改用户类型
     *
     * @param uid
     * @param accountType
     * @return 修改行数
     */
    public Integer updateAccountType(int uid, String accountType);

    /**
     * 修改学校
     *
     * @param uid
     * @param college
     * @return 修改行数
     */
    public Integer updateCollege(int uid, String college);

    /**
     * 修改专业
     *
     * @param uid
     * @param major
     * @return 修改行数
     */
    public Integer updateMajor(int uid, String major);

    /**
     * 修改毕业时间
     *
     * @param uid
     * @param graduationDate
     * @return 修改行数
     */
    public Integer updateGraduateDate(int uid, Date graduationDate);

    /**
     * 修改预期专业
     *
     * @param uid
     * @param expectedMajor
     * @return 修改行数
     */
    public Integer updateExpectedMajor(int uid, String expectedMajor);

    /**
     * @param uid
     * @param queryable
     * @return 修改行数
     */
    public Integer updateQueryable(int uid, boolean queryable);

    /**
     * 获取学生信息
     *
     * @param uid
     * @return StudentMsg
     */
    JsonResult getStudentMsg(String uid);

    /**
     * 修改学生信息
     *
     * @param phone
     * @param email
     * @param college
     * @param major
     * @param graduationDate
     * @param expectedMajor
     * @param queryable
     * @param id
     * @return 修改后的结果
     */
    JsonResult updateStudentMsg(String phone, String email, String college, String major, String graduationDate, String expectedMajor,
                                String queryable, String id);
}
