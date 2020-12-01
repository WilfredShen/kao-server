package com.kao.server.service.impl;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 全鸿润
 */
@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorMapper tutorMapper;

    @Override
    public TutorMessage findTutorById(int uid) {
        return tutorMapper.findTutorById(uid);
    }

    @Override
    public TutorMessage getTutorMsg(int uid) {

        return tutorMapper.findTutorById(uid);
    }

    @Override
    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor) {
        return tutorMapper.getQueryableStudentByConditions(
                beginDate,
                endDate,
                collegeLevel,
                major,
                expectedMajor
        );
    }

    @Override
    public Integer updateTutorMsg(UpdatedTutorMessage message, int uid) {
        return tutorMapper.updateTutorMessage(message, uid);
    }

}
