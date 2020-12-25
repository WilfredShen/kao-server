package com.kao.server.service.impl;

import com.kao.server.dto.*;
import com.kao.server.entity.College;
import com.kao.server.mapper.BaseQueryMapper;
import com.kao.server.service.BaseQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#round")
    public List<EvaluationBase> queryEvaluation(Integer round) {
        List<EvaluationBase> data = null;
        try {
            System.err.println("queryEvaluation:"+round);
            data = baseQueryMapper.queryEvaluation(round);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<EvaluationBase> queryLatestEvaluation() {
        List<EvaluationBase> data = null;
        try {
            System.err.println("queryLatestEvaluation");
            data = baseQueryMapper.queryLatestEvaluation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
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
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#cidList.hashCode()")
    public List<College> queryCollege(List<String> cidList) {
        List<College> data = null;
        try {
            System.err.println("queryCollege");
            data = baseQueryMapper.queryCollege(cidList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#cid")
    public List<TutorRoleBaseWithName> queryTutor(String cid) {
        List<TutorRoleBaseWithName> data = null;
        try {
            System.err.println("queryCollege:"+cid);
            data = baseQueryMapper.queryTutor(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<SummerCampMessage> querySummerCamp() {

        List<SummerCampMessage> data = null;
        try {
            System.err.println("querySummerCamp");
            data = baseQueryMapper.querySummerCamp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<ExemptionMessage> queryExemption() {
        List<ExemptionMessage> data = null;
        try {
            System.err.println("queryExemption");
            data = baseQueryMapper.queryExemption();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#cid")
    public List<LatestCollegeRank> queryLatestCollegeRank(String cid) {
        List<LatestCollegeRank> data = null;
        try {
            System.err.println("queryLatestCollegeRank:"+cid);
            data = baseQueryMapper.queryLatestCollegeRank(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName")
    public List<CollegeRankBase> queryCollegeRank() {
        try {
            System.err.println("queryCollegeRank");
            return baseQueryMapper.queryCollegeRank();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#cid")
    public List<AcceptanceRateMessage> queryAcceptanceRate(String cid) {
        List<AcceptanceRateMessage> data = null;
        try {
            System.err.println("queryAcceptanceRate:"+cid);
            data = baseQueryMapper.queryAcceptanceRate(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
