package com.kao.server.service.impl;

import com.kao.server.dto.*;
import com.kao.server.entity.College;
import com.kao.server.mapper.BaseQueryMapper;
import com.kao.server.service.BaseQueryService;
import com.kao.server.util.properties.RedisPrefixProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 沈伟峰
 */
@Service
public class BaseQueryServiceImpl implements BaseQueryService {

    @Autowired
    BaseQueryMapper baseQueryMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public List<EvaluationBase> queryEvaluation(Integer round) {
        List<EvaluationBase> data = null;
        String key = RedisPrefixProperties.EVALUATIONS + round;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<EvaluationBase>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryEvaluation(round);
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<EvaluationBase> queryLatestEvaluation() {
        List<EvaluationBase> data = null;
        String key = RedisPrefixProperties.LATEST_EVALUATION;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<EvaluationBase>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryLatestEvaluation();
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<NewsBase> queryLatestNews(Integer limit) {
        List<NewsBase> data = null;
        String key = RedisPrefixProperties.LATEST_NEWS;
        if (limit <= 0) {
            limit = Integer.MAX_VALUE;
        }
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<NewsBase>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryLatestNews(limit);
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
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
    public List<TutorRoleBaseWithName> queryTutor(String cid) {
        List<TutorRoleBaseWithName> data = null;
        String key = RedisPrefixProperties.TUTOR_WITH_CID + cid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<TutorRoleBaseWithName>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryTutor(cid);
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<SummerCampMessage> querySummerCamp() {

        List<SummerCampMessage> data = null;
        String key = RedisPrefixProperties.SUMMER_CAMP;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<SummerCampMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.querySummerCamp();
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<ExemptionMessage> queryExemption() {
        List<ExemptionMessage> data = null;
        String key = RedisPrefixProperties.EXEMPTION;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<ExemptionMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryExemption();
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<LatestCollegeRank> queryLatestCollegeRank(String cid) {
        List<LatestCollegeRank> data = null;
        String key = RedisPrefixProperties.COLLEGE_LATEST_RANK + cid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<LatestCollegeRank>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryLatestCollegeRank(cid);
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<CollegeRankBase> queryCollegeRank() {

        List<CollegeRankBase> data = null;
        String key = RedisPrefixProperties.COLLEGE_RANK;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<CollegeRankBase>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryCollegeRank();
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<AcceptanceRateMessage> queryAcceptanceRate(String cid) {
        List<AcceptanceRateMessage> data = null;
        String key = RedisPrefixProperties.ACCEPTANCE_RATE + cid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<AcceptanceRateMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = baseQueryMapper.queryAcceptanceRate(cid);
                redisTemplate.opsForValue().set(key, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
