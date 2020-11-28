package com.kao.server.service.impl;

import com.kao.server.dto.MajorFavorBase;
import com.kao.server.dto.MajorFavorMessage;
import com.kao.server.dto.NewsFavorMessage;
import com.kao.server.dto.StudentId;
import com.kao.server.dto.TutorFavorBase;
import com.kao.server.dto.TutorFavorMessage;
import com.kao.server.mapper.FavorMapper;
import com.kao.server.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public boolean favorMajor(String stuCid, String stuSid, List<MajorFavorBase> majorList) {
        try {
            int count = favorMapper.favorMajor(stuCid, stuSid, majorList);
            if (count != majorList.size()) {
                throw new RuntimeException("未全部完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    @Transactional
    public boolean favorTutor(String stuCid, String stuSid, List<TutorFavorBase> tutorList) {
        try {
            int count = favorMapper.favorTutor(stuCid, stuSid, tutorList);
            if (count != tutorList.size()) {
                throw new RuntimeException("未全部完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
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
}
