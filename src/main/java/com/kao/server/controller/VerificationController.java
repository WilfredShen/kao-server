package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.VerificationService;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/vf")
public class VerificationController {

    @Autowired
    private VerificationService verificationService;

    /**
     * 实名认证
     *
     * @param jsonObject 请求数据
     * @param request    HTTP 请求
     * @return 请求结果
     */
    @PostMapping("/real")
    @IsLoggedIn
    public JsonResult realAuth(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        String identity = jsonObject.getString("identity");
        String name = jsonObject.getString("name");
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

    /**
     * 学生身份认证
     *
     * @param jsonObject 请求数据
     * @param request    HTTP 请求
     * @return 请求结果
     */
    @PostMapping("/student")
    @IsLoggedIn
    public JsonResult studentAuth(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        String cid = jsonObject.getString("cid");
        String sid = jsonObject.getString("sid");
        int flag = verificationService.studentAuth(uid, cid, sid);
        System.out.println("cid: " + cid);
        System.out.println("sid: " + sid);
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

    /**
     * 导师身份认证
     *
     * @param jsonObject 请求数据
     * @param request    HTTP 请求
     * @return 请求结果
     */
    @PostMapping("/tutor")
    @IsLoggedIn
    public JsonResult tutorAuth(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        String cid = jsonObject.getString("cid");
        String tid = jsonObject.getString("tid");
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
