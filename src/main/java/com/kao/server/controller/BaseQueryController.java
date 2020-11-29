package com.kao.server.controller;

import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.dto.TutorRoleBaseWithName;
import com.kao.server.entity.College;
import com.kao.server.service.BaseQueryService;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/base")
public class BaseQueryController {

    @Autowired
    BaseQueryService baseQueryService;

    @GetMapping("/evaluation")
    public JsonResult queryEvaluation(@RequestParam(required = false) Integer round) {
        JsonResult jsonResult;
        List<EvaluationBase> data;
        if (round == null || round == 0) {
            data = baseQueryService.queryLatestEvaluation();
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        } else if (round > 0) {
            data = baseQueryService.queryEvaluation(round);
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }
        return jsonResult;
    }

    @GetMapping("/latest-news")
    public JsonResult queryLatestNews() {
        JsonResult jsonResult;
        List<NewsBase> data = baseQueryService.queryLatestNews(6);
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/college")
    public JsonResult queryCollege(@RequestParam() String cid) {
        JsonResult jsonResult;
        College data = baseQueryService.queryCollege(cid);
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/tutor")
    public JsonResult queryTutor(@RequestParam() String cid) {
        JsonResult jsonResult;
        List<TutorRoleBaseWithName> data = baseQueryService.queryTutor(cid);
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }
}
