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

    /**
     * 查询学生 id
     *
     * @param uid 用户 id
     * @return 查询结果
     */
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

    /**
     * 收藏专业
     *
     * @param majorList 某学校学生待收藏的专业
     * @return 收藏是否成功
     */
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

    /**
     * 收藏导师
     *
     * @param tutorList 某学校学生待收藏的导师
     * @return 收藏是否成功
     */
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

    /**
     * 查询收藏的学校的新闻
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
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

    /**
     * 查询收藏的专业
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
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

    /**
     * 查询收藏的导师
     *
     * @param stuCid 学生学校编号
     * @param stuSid 学生编号
     * @return 查询结果
     */
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
