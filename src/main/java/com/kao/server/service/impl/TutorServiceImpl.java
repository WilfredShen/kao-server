package com.kao.server.service.impl;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.mapper.TutorMapper;
import com.kao.server.service.TutorService;
import com.kao.server.util.checker.TutorMsgChecker;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    TutorMapper tutorMapper;

    @Override
    public TutorMessage findTutorById(int uid) {
        return tutorMapper.findTutorById(uid);
    }

    @Override
    public Integer updatePhone(int uid, String phone) {
        return tutorMapper.updatePhone(uid, phone);
    }

    @Override
    public Integer updateEmail(int uid, String email) {
        return tutorMapper.updateEmail(uid, email);
    }

    @Override
    public Integer updateCollege(int uid, String college) {
        return tutorMapper.updateCollege(uid, college);
    }

    @Override
    public Integer updateMajor(int uid, String major) {
        return tutorMapper.updateMajor(uid, major);
    }

    @Override
    public Integer updateResearch(int uid, String research) {
        return tutorMapper.updateResearch(uid, research);
    }

    @Override
    public JsonResult getTutorMsg(String uid) {

        TutorMessage tutorMessage = tutorMapper.findTutorById(Integer.parseInt(uid));
        if (tutorMessage != null) {
            return ResultFactory.buildSuccessJsonResult("获取成功", tutorMessage);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED_USER, "越权访问");
        }
    }

    @Override
    public List<QueryableStudentMessage> getQueryableStudentByConditions(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor) {
        return tutorMapper.getQueryableStudentByConditions(beginDate, endDate, collegeLevel, major, expectedMajor);
    }

    @Override
    public JsonResult updateTutorMsg(String phone, String email, String college, String major, String id, String research) {

        return TutorMsgChecker.checkTutorMsg(phone, email, college, major, this, research, id);
    }

    public JsonResult getQueryableStudentMsg(Date beginDate, Date endDate, String collegeLevel, String major, String expectedMajor) {

        List<QueryableStudentMessage> data = tutorMapper.getQueryableStudentByConditions(beginDate, endDate, collegeLevel, major, expectedMajor);
        if (data != null) {
            return ResultFactory.buildSuccessJsonResult("query success", data);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, "query failed");
        }
    }
}
