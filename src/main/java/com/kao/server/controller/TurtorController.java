package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.TutorServiceImpl;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.intercepter.IsTutor;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        String uid = request.getParameter("uid");
        return tutorService.getTutorMsg(uid);
    }

    @RequestMapping(value = "/update_tutor_msg", method = RequestMethod.POST)
    @ResponseBody
    @IsLoggedIn
    @IsTutor
    public JsonResult updateTutorMsg(@RequestBody JSONObject tutorMsg, HttpServletRequest request) {

        String phone = tutorMsg.getString("phoneNumber");
        String email = tutorMsg.getString("email");
        String college = tutorMsg.getString("college");
        String major = tutorMsg.getString("major");
        String id = request.getHeader("uid");
        String research = tutorMsg.getString("research");
        return tutorService.updateTutorMsg(phone, email, college, major, id, research);
    }

    @RequestMapping(value = "/get_queryableStu_msg", method = RequestMethod.GET)
    @ResponseBody
    @IsLoggedIn
    @IsTutor
    public JsonResult getQueryableStudentByConditions(HttpServletRequest request) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        if (request.getParameter("beginDate") != null && request.getParameter("endDate") != null) {
            try {
                beginDate = format.parse(request.getParameter("beginDate"));
                endDate = format.parse(request.getParameter("endDate"));
            } catch (ParseException e) {
                System.err.println(request.getParameter("beginDate"));
                e.printStackTrace();
            }
        }
        String collegeLevel = request.getParameter("collegeLevel");
        String major = request.getParameter("major");
        String expectedMajor = request.getParameter("expectedMajor");

        return tutorService.getQueryableStudentMsg(beginDate, endDate, collegeLevel, major, expectedMajor);
    }

}
