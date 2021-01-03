package com.kao.server.service.impl;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.dto.RankBase;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import com.kao.server.mapper.MetaQueryMapper;
import com.kao.server.service.MetaQueryService;
import com.kao.server.util.properties.RedisPrefixProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 沈伟峰
 */
@Service
@PropertySource(value = {"classpath:application.yml"})
public class MetaQueryServiceImpl implements MetaQueryService {

    @Autowired
    private MetaQueryMapper metaQueryMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    @Override
    public List<CollegeIdAndName> queryCollegeIdAndName() {
        List<CollegeIdAndName> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.COLLEGE_ID_AND_NAME);
            if (flag != null && flag) {
                data = (List<CollegeIdAndName>) redisTemplate.opsForValue().get(RedisPrefixProperties.COLLEGE_ID_AND_NAME);
            } else {
                data = metaQueryMapper.queryCollegeIdAndName();
                redisTemplate.opsForValue().set(RedisPrefixProperties.COLLEGE_ID_AND_NAME, data);
                redisTemplate.expire(RedisPrefixProperties.COLLEGE_ID_AND_NAME, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Discipline> queryDiscipline() {
        List<Discipline> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.DISCIPLINE);
            if (flag != null && flag) {
                data = (List<Discipline>) redisTemplate.opsForValue().get(RedisPrefixProperties.DISCIPLINE);
            } else {
                data = metaQueryMapper.queryDiscipline();
                redisTemplate.opsForValue().set(RedisPrefixProperties.DISCIPLINE, data);
                redisTemplate.expire(RedisPrefixProperties.DISCIPLINE, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Major> queryMajor() {
        List<Major> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.MAJOR);
            if (flag != null && flag) {
                data = (List<Major>) redisTemplate.opsForValue().get(RedisPrefixProperties.MAJOR);
            } else {
                data = metaQueryMapper.queryMajor();
                redisTemplate.opsForValue().set(RedisPrefixProperties.MAJOR, data);
                redisTemplate.expire(RedisPrefixProperties.MAJOR, expireTime, TimeUnit.MINUTES);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<Integer> queryRound() {
        List<Integer> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.ROUND);
            if (flag != null && flag) {
                data = (List<Integer>) redisTemplate.opsForValue().get(RedisPrefixProperties.ROUND);
            } else {
                data = metaQueryMapper.queryRound();
                redisTemplate.opsForValue().set(RedisPrefixProperties.ROUND, data);
                redisTemplate.expire(RedisPrefixProperties.ROUND, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<RankBase> queryRank() {

        List<RankBase> data = null;
        try {
            Boolean flag = redisTemplate.hasKey(RedisPrefixProperties.RANK);
            if (flag != null && flag) {
                data = (List<RankBase>) redisTemplate.opsForValue().get(RedisPrefixProperties.RANK);
            } else {
                data = metaQueryMapper.queryRank();
                redisTemplate.opsForValue().set(RedisPrefixProperties.RANK, data);
                redisTemplate.expire(RedisPrefixProperties.RANK, expireTime, TimeUnit.MINUTES);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
