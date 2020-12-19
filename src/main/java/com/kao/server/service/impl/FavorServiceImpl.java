package com.kao.server.service.impl;

import com.kao.server.dto.*;
import com.kao.server.mapper.FavorMapper;
import com.kao.server.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沈伟峰
 */
@Service
public class FavorServiceImpl implements FavorService {

    @Autowired
    private FavorMapper favorMapper;

    @Override
    public StudentId getStudentId(int uid) {
        StudentId data = null;
        try {
            data = favorMapper.getStudentId(uid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Integer favorMajor(String stuCid, String stuSid, List<MajorFavorBase> majorList) {
        Integer count = null;
        try {
            count = favorMapper.favorMajor(stuCid, stuSid, majorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer favorTutor(String stuCid, String stuSid, List<TutorFavorBase> tutorList) {
        Integer count = null;
        try {
            count = favorMapper.favorTutor(stuCid, stuSid, tutorList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<NewsFavorMessage> queryNews(String stuCid, String stuSid) {
        List<NewsFavorMessage> data = null;
        try {
            data = favorMapper.queryNews(stuCid, stuSid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<MajorFavorMessage> queryMajor(String stuCid, String stuSid) {
        List<MajorFavorMessage> data = null;
        try {
            data = favorMapper.queryMajor(stuCid, stuSid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<TutorFavorMessage> queryTutor(String stuCid, String stuSid) {
        List<TutorFavorMessage> data = null;
        try {
            data = favorMapper.queryTutor(stuCid, stuSid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Integer deleteMajor(String cid, String sid, String majorCid, String majorMid) {
        try {
            return favorMapper.deleteMajor(cid, sid, majorCid, majorMid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer deleteTutor(String cid, String sid, String tutorCid, String tutorTid) {
        try {
            return favorMapper.deleteTutor(cid, sid, tutorCid, tutorTid);
        } catch (Exception e) {
            return null;
        }
    }
}
