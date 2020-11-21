package com.kao.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.StudentMessage;
import com.kao.server.mapper.StudentMapper;
import com.kao.server.service.StudentService;
import com.kao.server.util.checker.StuMsgChecker;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;
    @Override
    public StudentMessage getstuMsg(int uid) {
        return studentMapper.findStudentById(uid);
    }

    @Override
    public Integer updatePhone(int uid, String phone) {
        return studentMapper.updatePhone(uid,phone);
    }

    @Override
    public Integer updateEmail(int uid, String email) {
        return studentMapper.updateEmail(uid,email);
    }

    @Override
    public Integer updateAccountType(int uid, String accountType) {
        return studentMapper.updateAccountType(uid,accountType);
    }

    @Override
    public Integer updateCollege(int uid, String college) {
        return studentMapper.updateCollege(uid,college);
    }

    @Override
    public Integer updateMajor(int uid, String major) {
        return studentMapper.updateMajor(uid,major);
    }

    @Override
    public Integer updateGraduateDate(int uid, Date graduationDate) {
        return studentMapper.updateGraduateDate(uid,graduationDate);
    }

    @Override
    public Integer updateExpectedMajor(int uid, String expectedMajor) {
        return studentMapper.updateExpectedMajor(uid,expectedMajor);
    }

    @Override
    public Integer updateQueryable(int uid, boolean queryable) {
        return studentMapper.updateQueryable(uid,queryable);
    }
    public JsonResult getStudentMsg(HttpServletRequest request) {

        String uid = request.getParameter("uid");

        StudentMessage studentMessage = this.getstuMsg(Integer.parseInt(uid));
        if (studentMessage != null) {
            return ResultFactory.buildSuccessJsonResult("获取成功", studentMessage);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED_USER, "越权访问");
        }

    }
    public JsonResult updateStudentMsg(@RequestBody JSONObject studentMsg, HttpServletRequest request) {

        String phone = studentMsg.getString("phoneNumber");
        String email = studentMsg.getString("email");
        String college = studentMsg.getString("college");
        String major = studentMsg.getString("major");
        String graduationDate = studentMsg.getString("graduationDate");
        String expectedMajor = studentMsg.getString("expectedMajor");
        String queryable = studentMsg.getString("queryable");
        String id = request.getHeader("uid");

        return StuMsgChecker.checkStuMsg(phone, email, college, major, graduationDate, expectedMajor, queryable, id, this);
    }
}
