package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.TutorServiceImpl;
import com.kao.server.util.intercepter.IsTutor;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.intercepter.IsLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/tutor")
public class TurtorController {

    @Autowired
    TutorServiceImpl tutorService;

    @RequestMapping(value = "/get_tutor_msg", method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsTutor
    public JsonResult getTutorMsg(HttpServletRequest request) {
        return tutorService.getTutorMsg(request);
    }

    @RequestMapping(value = "/update_tutor_msg", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    @IsTutor
    public JsonResult updateTutorMsg(@RequestBody JSONObject tutorMsg, HttpServletRequest request) {
        return tutorService.updateTutorMsg(tutorMsg, request);
    }

    @RequestMapping(value = "/get_queryableStu_msg", method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsTutor
    public JsonResult getQueryableStudentByConditions(HttpServletRequest request) {
        return tutorService.getQueryableStudentMsg(request);
    }

}
