package com.kao.server.service.impl;

import com.kao.server.dto.*;
import com.kao.server.mapper.FavorMapper;
import com.kao.server.service.FavorService;
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
public class FavorServiceImpl implements FavorService {

    @Autowired
    private FavorMapper favorMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    @Override
    public StudentId getStudentId(Integer uid) {
        StudentId data = null;
        String key = RedisPrefixProperties.STUDENT_ID;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (StudentId) redisTemplate.opsForValue().get(key);
            } else {
                data = favorMapper.getStudentId(uid);
                redisTemplate.opsForValue().set(key, data);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Integer favorMajor(String stuCid, String stuSid, List<MajorFavorBase> majorList) {
        Integer count = null;
        String key1 = RedisPrefixProperties.FAVOR_MAJOR + stuCid + stuSid;
        String key2 = RedisPrefixProperties.FAVOR_NEWS + stuCid + stuSid;
        try {
            System.err.println("favorMajor:" + stuCid + "," + stuSid);
            count = favorMapper.favorMajor(stuCid, stuSid, majorList);
            if (count != null) {
                redisTemplate.opsForValue().set(key1, favorMapper.queryMajor(stuCid, stuSid));
                redisTemplate.opsForValue().set(key2, favorMapper.queryNews(stuCid, stuSid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(key2, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Integer favorTutor(String stuCid, String stuSid, List<TutorFavorBase> tutorList) {
        Integer count = null;
        String key1 = RedisPrefixProperties.FAVOR_TUTOR + stuCid + stuSid;
        try {
            System.err.println("favorTutor:" + stuCid + "," + stuSid);
            count = favorMapper.favorTutor(stuCid, stuSid, tutorList);
            if (count != null) {
                redisTemplate.opsForValue().set(key1, favorMapper.queryTutor(stuCid, stuSid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<NewsFavorMessage> queryNews(String stuCid, String stuSid) {
        List<NewsFavorMessage> data = null;
        String key = RedisPrefixProperties.FAVOR_NEWS + stuCid + stuSid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<NewsFavorMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = favorMapper.queryNews(stuCid, stuSid);
                redisTemplate.opsForValue().set(key, data);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<MajorFavorMessage> queryMajor(String stuCid, String stuSid) {
        List<MajorFavorMessage> data = null;
        String key = RedisPrefixProperties.FAVOR_MAJOR + stuCid + stuSid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<MajorFavorMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = favorMapper.queryMajor(stuCid, stuSid);
                redisTemplate.opsForValue().set(key, data);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public List<TutorFavorMessage> queryTutor(String stuCid, String stuSid) {
        List<TutorFavorMessage> data = null;
        String key = RedisPrefixProperties.FAVOR_TUTOR + stuCid + stuSid;
        try {
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                data = (List<TutorFavorMessage>) redisTemplate.opsForValue().get(key);
            } else {
                data = favorMapper.queryTutor(stuCid, stuSid);
                redisTemplate.opsForValue().set(key, data);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Integer deleteMajor(String cid, String sid, String majorCid, String majorMid) {

        Integer row = null;
        String key1 = RedisPrefixProperties.FAVOR_MAJOR + cid + sid;
        String key2 = RedisPrefixProperties.FAVOR_NEWS + cid + sid;
        try {
            row = favorMapper.deleteMajor(cid, sid, majorCid, majorMid);
            if (row != null && row == 1) {
                redisTemplate.opsForValue().set(key1, favorMapper.queryMajor(cid, sid));
                redisTemplate.opsForValue().set(key2, favorMapper.queryNews(cid, sid));
                redisTemplate.expire(key1, expireTime, TimeUnit.MINUTES);
                redisTemplate.expire(key2, expireTime, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public Integer deleteTutor(String cid, String sid, String tutorCid, String tutorTid) {

        Integer row = null;
        String key = RedisPrefixProperties.FAVOR_TUTOR + cid + sid;
        try {
            row = favorMapper.deleteTutor(cid, sid, tutorCid, tutorTid);
            if (row != null && row == 1) {
                redisTemplate.opsForValue().set(key, favorMapper.queryTutor(cid, sid));
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }
}
