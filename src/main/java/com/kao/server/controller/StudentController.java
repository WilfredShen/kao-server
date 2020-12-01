package com.kao.server.controller;

import com.kao.server.dto.StudentMessage;
import com.kao.server.dto.UpdatedStudentMessage;
import com.kao.server.service.StudentService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.intercepter.IsStudent;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
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
    StudentService studentService;

    @RequestMapping(value = "/get_stu_info", method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult getStudentMsg(HttpServletRequest request) {

        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentMessage studentMessage = studentService.getStuMsg(uid);
        if (studentMessage != null) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, studentMessage);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNAUTHORIZED_DESC);
        }
    }

    @RequestMapping(value = "/update_stu_info", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    @IsStudent
    public JsonResult updateStudentMsg(@RequestBody UpdatedStudentMessage studentMessage, HttpServletRequest request) {

        try {
            Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
            if (studentService.getStuMsg(uid) == null) {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNAUTHORIZED_DESC);
            }
            if (studentService.updateStudentMsg(studentMessage, uid) == 1) {
                return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }

    }
}
