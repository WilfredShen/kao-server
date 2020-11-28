package com.kao.server.mapper;

import com.kao.server.dto.MajorFavorBase;
import com.kao.server.dto.MajorFavorMessage;
import com.kao.server.dto.NewsFavorMessage;
import com.kao.server.dto.StudentId;
import com.kao.server.dto.TutorFavorBase;
import com.kao.server.dto.TutorFavorMessage;

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
}