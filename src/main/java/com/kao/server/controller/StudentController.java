package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.util.intercepter.IsStudent;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.intercepter.IsLoggedIn;
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
    @RequestMapping(value = "/get_stu_info" ,method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult getStudentMsg(HttpServletRequest request) {

        return studentService.getStudentMsg(request);

    }

    @RequestMapping(value = "/update_stu_info", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult updateStudentMsg(@RequestBody JSONObject studentMsg, HttpServletRequest request) {

        return studentService.updateStudentMsg(studentMsg, request);
    }
}
