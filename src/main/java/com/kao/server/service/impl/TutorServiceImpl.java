package com.kao.server.service.impl;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorMapper tutorMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final String prefix = "tutor";

    @Override
    public TutorMessage findTutorById(Integer uid) {
        try {
            System.err.println("findTutorById: " + uid);
            return tutorMapper.findTutorById(uid);
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
            Integer raw = tutorMapper.updateTutorMessage(message, uid);
            if (raw != null && raw == 1) {
                redisTemplate.opsForValue().set(key, tutorMapper.findTutorById(uid));
            }
            return raw;
        } catch (Exception e) {
            return null;
        }
    }

}