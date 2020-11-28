package com.kao.server.mapper;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;

import java.util.Date;
import java.util.List;

public interface TutorMapper {

    public TutorMessage findTutorById(int uid);

    public Integer updatePhone(int uid, String phone);

    public Integer updateEmail(int uid, String email);

    public Integer updateCollege(int uid, String college);

    public Integer updateMajor(int uid, String major);

    public Integer updateResearch(int uid, String research);

    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String CollegeLevel, String major, String expectedMajor);
}
