package com.kao.server.service.impl;

import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.dto.TutorRoleBaseWithName;
import com.kao.server.entity.College;
import com.kao.server.mapper.BaseQueryMapper;
import com.kao.server.service.BaseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沈伟峰
 */
@Service
public class BaseQueryServiceImpl implements BaseQueryService {

    @Autowired
    BaseQueryMapper baseQueryMapper;

    /**
     * 获得指定轮次的所有评估结果
     *
     * @param round 轮次
     * @return 评估结果
     */
    @Override
    public List<EvaluationBase> queryEvaluation(Integer round) {
        List<EvaluationBase> data = null;
        try {
            data = baseQueryMapper.queryEvaluation(round);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获得最新轮次的所有评估结果
     *
     * @return 评估结果
     */
    @Override
    public List<EvaluationBase> queryLatestEvaluation() {
        List<EvaluationBase> data = null;
        try {
            data = baseQueryMapper.queryLatestEvaluation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取最新的新闻（根据日期降序排序）
     *
     * @param limit 限定获取的数量，0或负数表示全部
     * @return 新闻
     */
    @Override
    public List<NewsBase> queryLatestNews(Integer limit) {
        List<NewsBase> data = null;
        if (limit <= 0) {
            limit = Integer.MAX_VALUE;
        }
        try {
            data = baseQueryMapper.queryLatestNews(limit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取指定学校的详情
     *
     * @param cid 学校代码
     * @return 学校详情
     */
    @Override
    public College queryCollege(String cid) {
        College college = null;
        try {
            college = baseQueryMapper.queryCollege(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return college;
    }

    /**
     * 获得指定学校的所有导师
     *
     * @param cid 学校代码
     * @return 导师
     */
    @Override
    public List<TutorRoleBaseWithName> queryTutor(String cid) {
        List<TutorRoleBaseWithName> data = null;
        try {
            data = baseQueryMapper.queryTutor(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
