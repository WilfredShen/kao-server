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
        boolean flag = verificationService.realAuth(uid, identity, name);
        JsonResult jsonResult;
        if (flag) {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
        return jsonResult;
    }

    @PostMapping("/student")
    @IsLoggedIn
    public JsonResult studentAuth(String cid, String sid, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        boolean flag = verificationService.studentAuth(uid, cid, sid);
        JsonResult jsonResult;
        if (flag) {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
        return jsonResult;
    }

    @PostMapping("/tutor")
    @IsLoggedIn
    public JsonResult tutorAuth(String cid, String tid, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        boolean flag = verificationService.tutorAuth(uid, cid, tid);
        JsonResult jsonResult;
        if (flag) {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
        return jsonResult;
    }
}
