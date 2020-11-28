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
import org.springframework.web.bind.annotation.*;

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
        List<EvaluationBase> data;
        if (round == null || round == 0) {
            data = baseQueryService.queryLatestEvaluation();
        } else if (round > 0) {
            data = baseQueryService.queryEvaluation(round);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }
        return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
    }

    @GetMapping("/latest-news")
    public JsonResult queryLatestNews() {
        List<NewsBase> data = baseQueryService.queryLatestNews(6);
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
    }

    @GetMapping("/college")
    public JsonResult queryCollege(@RequestParam() String cid) {
        College data = baseQueryService.queryCollege(cid);
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
    }

    @GetMapping("/tutor")
    public JsonResult queryTutor(@RequestParam() String cid) {
        List<TutorRoleBaseWithName> data = baseQueryService.queryTutor(cid);
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
    }
}
