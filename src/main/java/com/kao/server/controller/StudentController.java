package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.intercepter.IsStudent;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@RequestMapping(value = "/stu")
@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(value = "/get_stu_info", method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult getStudentMsg(HttpServletRequest request) {

        String uid = request.getParameter("uid");
        return studentService.getStudentMsg(uid);
    }

    @RequestMapping(value = "/update_stu_info", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult updateStudentMsg(@RequestBody JSONObject studentMsg, HttpServletRequest request) {

        String phone = studentMsg.getString("phoneNumber");
        String email = studentMsg.getString("email");
        String college = studentMsg.getString("college");
        String major = studentMsg.getString("major");
        String graduationDate = studentMsg.getString("graduationDate");
        String expectedMajor = studentMsg.getString("expectedMajor");
        String queryable = studentMsg.getString("queryable");
        String id = request.getHeader("uid");
        return studentService.updateStudentMsg(
                phone,
                email,
                college,
                major,
                graduationDate,
                expectedMajor,
                queryable,
                id
        );
    }
}
