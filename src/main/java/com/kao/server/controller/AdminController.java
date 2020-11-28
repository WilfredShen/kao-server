package com.kao.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.service.AdminService;
import com.kao.server.util.intercepter.IsAdmin;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 全鸿润
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody JSONObject adminMsg) {
        String username = adminMsg.getString("username");
        String password = adminMsg.getString("password");
        System.err.println("Login:" + username + password);
        return adminService.handleLogin(username, password);
    }

    @RequestMapping(value = "/get_evaluation", method = RequestMethod.GET)
    @IsLoggedIn
    @IsAdmin
    public JsonResult getEvaluation(@RequestParam() int round) {
        List<AdminViewEvaluation> data = adminService.findEvaluationByRound(round);
        return ResultFactory.buildSuccessJsonResult("查找成功", data);
    }

    @RequestMapping(value = "/upload_evaluation", method = RequestMethod.POST)
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadEvaluations(@RequestBody JSONObject evaluations) {

        JSONArray res = evaluations.getJSONArray("evaluations");
        List<EvaluationBase> results = res.toJavaList(EvaluationBase.class);
        int len = results.size();
        if (len != adminService.uploadEvaluationResult(results)) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
        return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
    }

    @RequestMapping(value = "/update_evaluation", method = RequestMethod.POST)
    @IsLoggedIn
    @IsAdmin
    public JsonResult updateEvaluation(@RequestBody JSONObject evaluation) {

        String mid = evaluation.getString("mid");
        String cid = evaluation.getString("cid");
        String result = evaluation.getString("newResult");
        String adminId = evaluation.getString("adminId");
        String round = evaluation.getString("round");

        int raws = adminService.updateEvaluationResult(
                cid,
                mid,
                Integer.parseInt(round),
                Integer.parseInt(adminId),
                result
        );

        if (raws == 1) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        }

        return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);

    }

}
