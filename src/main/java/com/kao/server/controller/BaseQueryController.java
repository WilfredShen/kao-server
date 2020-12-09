package com.kao.server.controller;

import com.kao.server.dto.*;
import com.kao.server.entity.College;
import com.kao.server.service.BaseQueryService;
import com.kao.server.util.json.JsonResult;
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
    private BaseQueryService baseQueryService;

    @GetMapping("/evaluation")
    public JsonResult queryEvaluation(@RequestParam(required = false) Integer round) {
        JsonResult jsonResult;
        List<EvaluationBase> data;
        if (round == null || round <= 0) {
            data = baseQueryService.queryLatestEvaluation();
        } else {
            data = baseQueryService.queryEvaluation(round);
        }
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/latest-news")
    public JsonResult queryLatestNews() {
        JsonResult jsonResult;
        List<NewsBase> data = baseQueryService.queryLatestNews(6);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/college")
    public JsonResult queryCollege(@RequestParam List<String> cidList) {
        JsonResult jsonResult;
        List<College> data = baseQueryService.queryCollege(cidList);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/tutor")
    public JsonResult queryTutor(@RequestParam String cid) {
        JsonResult jsonResult;
        List<TutorRoleBaseWithName> data = baseQueryService.queryTutor(cid);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/summer-camp")
    public JsonResult querySummerCamp() {
        JsonResult jsonResult = null;
        List<SummerCampMessage> data = baseQueryService.querySummerCamp();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/exemption")
    public JsonResult queryExemption() {
        JsonResult jsonResult;
        List<ExemptionMessage> data = baseQueryService.queryExemption();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/acceptance-rate")
    public JsonResult queryEnrollmentRate(@RequestParam String cid) {
        JsonResult jsonResult;
        List<AcceptanceRateMessage> data = baseQueryService.queryAcceptanceRate(cid);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/latest-college-rank")
    public JsonResult queryLatestCollegeRank(@RequestParam String cid) {
        JsonResult jsonResult;
        List<LatestCollegeRank> data = baseQueryService.queryLatestCollegeRank(cid);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }
}
