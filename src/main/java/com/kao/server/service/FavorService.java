package com.kao.server.service;

import com.kao.server.dto.*;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface FavorService {

    /**
     * 查询学生 id
     *
     * @param uid 用户 id
     * @return 查询结果
     */
    StudentId getStudentId(int uid);

    /**
     * 收藏专业
     *
     * @param majorList 某学校学生待收藏的专业
     * @return 收藏成功的条数
     */
    Integer favorMajor(String stuCid, String stuSid, List<MajorFavorBase> majorList);

    /**
     * 收藏导师
     *
     * @param tutorList 某学校学生待收藏的导师
     * @return 收藏成功的条数
     */
    Integer favorTutor(String stuCid, String stuSid, List<TutorFavorBase> tutorList);

    /**
     * 查询收藏的学校的新闻
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<NewsFavorMessage> queryNews(String stuCid, String stuSid);

    /**
     * 查询收藏的专业
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<MajorFavorMessage> queryMajor(String stuCid, String stuSid);

    /**
     * 查询收藏的导师
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
    List<TutorFavorMessage> queryTutor(String stuCid, String stuSid);

    /**
     * 删除专业
     *
     * @param cid 学校代码
     * @param sid 学号
     * @return 删除的行数
     */
    Integer deleteMajor(String cid, String sid, String majorCid, String majorMid);

    /**
     * 删除导师
     *
     * @param cid 学校代码
     * @param sid 学号
     * @return 删除行数
     */
    Integer deleteTutor(String cid, String sid, String tutorCid, String tutorTid);
}
