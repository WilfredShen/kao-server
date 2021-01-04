package com.kao.server.service.impl;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@PropertySource(value = {"classpath:application.yml"})
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    private final String prefix = "tutor";

    @Override
    public TutorMessage findTutorById(Integer uid) {

        String key = prefix + uid;
        Boolean flag = redisTemplate.hasKey(key);
        TutorMessage tutorMessage = null;
        try {
            if (flag != null && flag) {
                tutorMessage = (TutorMessage) redisTemplate.opsForValue().get(key);
            } else {
                tutorMessage = tutorMapper.findTutorById(uid);
                redisTemplate.opsForValue().set(key, tutorMessage);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            System.err.println("findTutorById: " + uid);
            return tutorMessage;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TutorMessage getTutorMsg(Integer uid) {
        TutorMessage tutorMessage;
        try {
            String key = prefix + uid;
            System.err.println("getTutorMsg: " + uid);
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                tutorMessage = (TutorMessage) redisTemplate.opsForValue().get(key);
            } else {
                tutorMessage = tutorMapper.findTutorById(uid);
                redisTemplate.opsForValue().set(key, tutorMessage);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            return tutorMessage;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String
            collegeLevel, String major, String expectedMajor) {
        try {
            System.err.println("getQueryableStudentByConditions");
            return tutorMapper.getQueryableStudentByConditions(
                    beginDate,
                    endDate,
                    collegeLevel,
                    major,
                    expectedMajor
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateTutorMsg(UpdatedTutorMessage message, int uid) {
        try {
            String key = prefix + uid;
            Integer row = tutorMapper.updateTutorMessage(message, uid);
            if (row != null && row == 1) {
                redisTemplate.opsForValue().set(key, tutorMapper.findTutorById(uid));
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            return row;
        } catch (Exception e) {
            return null;
        }
    }

}