package com.kao.server.service.impl;

import com.kao.server.dto.*;
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

    @Override
    public List<College> queryCollege(List<String> cidList) {
        List<College> data = null;
        try {
            data = baseQueryMapper.queryCollege(cidList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

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

    @Override
    public List<SummerCampMessage> querySummerCamp() {

        List<SummerCampMessage> data = null;
        try {
            data = baseQueryMapper.querySummerCamp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<ExemptionMessage> queryExemption() {
        List<ExemptionMessage> data = null;
        try {
            data = baseQueryMapper.queryExemption();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<LatestCollegeRank> queryLatestCollegeRank(String cid) {
        List<LatestCollegeRank> data = null;
        try {
            data = baseQueryMapper.queryLatestCollegeRank(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<CollegeRankBase> queryCollegeRank() {
        try {
            return baseQueryMapper.queryCollegeRank();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<AcceptanceRateMessage> queryAcceptanceRate(String cid) {
        List<AcceptanceRateMessage> data = null;
        try {
            data = baseQueryMapper.queryAcceptanceRate(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
