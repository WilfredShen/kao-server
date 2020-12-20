package com.kao.server.mapper;

import com.kao.server.dto.*;

import java.util.List;

/**
 * @author 沈伟峰
 */
public interface FavorMapper {

    StudentId getStudentId(Integer uid) throws Exception;

    Integer favorMajor(String stuCid, String stuSid, List<MajorFavorBase> list) throws Exception;

    Integer favorTutor(String stuCid, String stuSid, List<TutorFavorBase> list) throws Exception;

    List<NewsFavorMessage> queryNews(String stuCid, String stuSid) throws Exception;

    List<MajorFavorMessage> queryMajor(String stuCid, String stuSid) throws Exception;

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
