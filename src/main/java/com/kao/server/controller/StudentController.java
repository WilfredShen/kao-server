package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.StudentMessage;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.util.checker.StuMsgChecker;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.login.IsLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@RequestMapping(value = "/stu")
@Controller
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping(value = "/stu_msg")
    @ResponseBody
    @IsLoggedIn
    public JsonResult getStudentMsg(HttpServletRequest request) {

        return studentService.getStudentMsg(request);

    }

    @RequestMapping(value = "/update_stu_msg", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    public JsonResult updateStudentMsg(@RequestBody JSONObject studentMsg, HttpServletRequest request) {

        return studentService.updateStudentMsg(studentMsg,request);
    }
}
