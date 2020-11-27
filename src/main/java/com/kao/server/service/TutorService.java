package com.kao.server.service;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.util.json.JsonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public interface TutorService {

    public TutorMessage findTutorById(int uid);

    public Integer updatePhone(int uid, String phone);

    public Integer updateEmail(int uid, String email);

    public Integer updateCollege(int uid, String college);

    public Integer updateMajor(int uid, String major);

    public Integer updateResearch(int uid, String research);

    public JsonResult getTutorMsg(HttpServletRequest request);

    public JsonResult updateTutorMsg(JSONObject tutorMsg, HttpServletRequest request);

    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String CollegeLevel, String major, String expectedMajor);
}
