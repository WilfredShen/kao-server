package com.kao.server.mapper;

import com.kao.server.dto.*;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface FavorMapper {

    /**
     * 查询学生 id
     *
     * @param uid 用户 id
     * @return 查询结果
     */
    StudentId getStudentId(Integer uid) throws Exception;

    /**
     * 收藏专业
     *
     * @param stuCid 学生院校编号
     * @param stuSid 学生学号
     * @param list   某学校学生待收藏的专业
     * @return 收藏成功的条数
     */
    Integer favorMajor(String stuCid, String stuSid, List<MajorFavorBase> list) throws Exception;

    /**
     * 收藏导师
     *
     * @param stuCid 学生院校编号
     * @param stuSid 学生学号
     * @param list   某学校学生待收藏的导师
     * @return 收藏成功的条数
     */
    Integer favorTutor(String stuCid, String stuSid, List<TutorFavorBase> list) throws Exception;

    /**
     * 查询收藏的学校的新闻
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<NewsFavorMessage> queryNews(String stuCid, String stuSid) throws Exception;

    /**
     * 查询收藏的专业
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<MajorFavorMessage> queryMajor(String stuCid, String stuSid) throws Exception;

    /**
     * 查询收藏的导师
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<TutorFavorMessage> queryTutor(String stuCid, String stuSid) throws Exception;

    /**
     * 删除专业
     *
     * @param cid      学校代码
     * @param sid      学号
     * @param majorCid 专业所属学校代码
     * @param majorMid 专业代码
     * @return 删除的行数
     * @throws Exception 数据库操作异常
     */
    Integer deleteMajor(String cid, String sid, String majorCid, String majorMid) throws Exception;

    /**
     * 删除导师
     *
     * @param cid      学校代码
     * @param sid      学号
     * @param tutorCid 教师所属学校代码
     * @param tutorTid 教师工号
     * @return 删除行数
     * @throws Exception 数据库操作异常
     */
    Integer deleteTutor(String cid, String sid, String tutorCid, String tutorTid) throws Exception;
}
