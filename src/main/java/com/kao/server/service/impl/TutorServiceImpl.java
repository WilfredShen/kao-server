package com.kao.server.service.impl;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
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

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#uid")
    public TutorMessage findTutorById(Integer uid) {
        try {
            System.err.println("findTutorById:"+uid);
            return tutorMapper.findTutorById(uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#uid")
    public TutorMessage getTutorMsg(Integer uid) {

        try {
            System.err.println("getTutorMsg:"+uid);
            return tutorMapper.findTutorById(uid);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    @Cacheable(value = {"redisCacheManager"}, key = "#root.methodName+#p0+#p1+#p2+#p3+#p4")
    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor) {
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
            System.err.println("updateTutorMsg"+uid);
            return tutorMapper.updateTutorMessage(message, uid);
        } catch (Exception e) {
            return null;
        }
    }

}
