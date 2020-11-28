package com.kao.server.service;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.util.json.JsonResult;

import java.util.Date;
import java.util.List;

public interface TutorService {

    public TutorMessage findTutorById(int uid);

    public Integer updatePhone(int uid, String phone);

    public Integer updateEmail(int uid, String email);

    public Integer updateCollege(int uid, String college);

    public Integer updateMajor(int uid, String major);

    public Integer updateResearch(int uid, String research);

    public JsonResult getTutorMsg(String uid);

    public JsonResult updateTutorMsg(String phone, String email, String college, String major, String id, String research);

    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String CollegeLevel, String major, String expectedMajor);
}
