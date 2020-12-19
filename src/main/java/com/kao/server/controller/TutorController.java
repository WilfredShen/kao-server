package com.kao.server.controller;

import com.kao.server.dto.QueryableStudentMessage;
import com.kao.server.dto.TutorMessage;
import com.kao.server.dto.UpdatedTutorMessage;
import com.kao.server.service.TutorService;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.accounttype.IsTutor;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 全鸿润
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/tutor")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping("/q/tutor-info")
    @IsLoggedIn
    @IsTutor
    public JsonResult getTutorMsg(HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        TutorMessage data = tutorService.getTutorMsg(uid);
        if (data != null) {
            return ResultFactory.buildSuccessJsonResult(data);
        } else {
            return ResultFactory.buildFailJsonResult();
        }
    }

    @PostMapping("/u/tutor-info")
    @IsLoggedIn
    @IsTutor
    public JsonResult updateTutorMsg(@RequestBody UpdatedTutorMessage message, HttpServletRequest request) {

        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        if (tutorService.getTutorMsg(uid) == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNAUTHORIZED_DESC);
        }
        Integer raw = tutorService.updateTutorMsg(message, uid);
        if (raw != null && raw == 1) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult();
        }
    }

    @GetMapping("/q/queryable-stu-info")
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
        List<QueryableStudentMessage> data = tutorService.getQueryableStudentByConditions(beginDate, endDate, collegeLevel, major, expectedMajor);
        JsonResult jsonResult;
        if (data != null) {
            jsonResult = ResultFactory.buildSuccessJsonResult(data);
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }
        return jsonResult;
    }

}
