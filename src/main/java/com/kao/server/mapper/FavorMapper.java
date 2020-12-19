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

    Integer deleteMajor(String cid, String sid, String majorCid, String majorMid) throws Exception;

    Integer deleteTutor(String cid, String sid, String tutorCid, String tutorTid) throws Exception;
}
