package com.kao.server.service.impl;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;
import com.kao.server.mapper.StudentMapper;
import com.kao.server.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author 全鸿润
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@PropertySource(value = {"classpath:application.yml"})
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${redis.key.expired.commandExpireTime}")
    private Long expireTime;

    private final String prefix = "stu";

    @Override
    public StudentMessage getStuMsg(Integer uid) {

        StudentMessage studentMessage;
        try {
            String key = prefix + uid;
            System.err.println("getStuMsg: " + uid);
            Boolean flag = redisTemplate.hasKey(key);
            if (flag != null && flag) {
                studentMessage = (StudentMessage) redisTemplate.opsForValue().get(key);
            } else {
                studentMessage = studentMapper.findStudentById(uid);
                redisTemplate.opsForValue().set(key, studentMessage);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            return studentMessage;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer updateStudentMsg(UpdatedStudentMessage studentMessage, int uid) {
        try {
            String key = prefix + uid;
            Integer row = studentMapper.updateStudentMsg(studentMessage, uid);
            if (row != null && row == 1) {
                StudentMessage message = studentMapper.findStudentById(uid);
                redisTemplate.opsForValue().set(key, message);
                redisTemplate.expire(key, expireTime, TimeUnit.MINUTES);
            }
            return row;
        } catch (Exception e) {
            return null;
        }
    }
}
