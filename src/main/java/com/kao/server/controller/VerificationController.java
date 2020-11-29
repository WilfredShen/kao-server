package com.kao.server.controller;

import com.kao.server.service.VerificationService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/vf")
public class VerificationController {

    @Autowired
    VerificationService verificationService;

    @PostMapping("/real")
    @IsLoggedIn
    public JsonResult realAuth(String identity, String name, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        int flag = verificationService.realAuth(uid, identity, name);
        JsonResult jsonResult;
        if (flag == JsonResultStatus.SUCCESS) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else if (flag == JsonResultStatus.REAL_AUTH_FAILED) {
            jsonResult = ResultFactory.buildFailJsonResult("REAL_AUTH_FAILED");
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }
        return jsonResult;
    }

    @PostMapping("/student")
    @IsLoggedIn
    public JsonResult studentAuth(String cid, String sid, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        int flag = verificationService.studentAuth(uid, cid, sid);
        JsonResult jsonResult;
        if (flag == JsonResultStatus.SUCCESS) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else if (flag == JsonResultStatus.STUDENT_AUTH_FAILED) {
            jsonResult = ResultFactory.buildFailJsonResult("STUDENT_AUTH_FAILED");
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }
        return jsonResult;
    }

    @PostMapping("/tutor")
    @IsLoggedIn
    public JsonResult tutorAuth(String cid, String tid, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        int flag = verificationService.tutorAuth(uid, cid, tid);
        JsonResult jsonResult;
        if (flag == JsonResultStatus.SUCCESS) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else if (flag == JsonResultStatus.TUTOR_AUTH_FAILED) {
            jsonResult = ResultFactory.buildFailJsonResult("TUTOR_AUTH_FAILED");
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }
        return jsonResult;
    }
}
