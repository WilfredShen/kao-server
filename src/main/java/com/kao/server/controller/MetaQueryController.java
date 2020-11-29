package com.kao.server.controller;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import com.kao.server.service.MetaQueryService;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/meta")
public class MetaQueryController {

    @Autowired
    MetaQueryService metaQueryService;

    @GetMapping("/college")
    public JsonResult queryCollegeIdAndName() {
        JsonResult jsonResult;
        List<CollegeIdAndName> data = metaQueryService.queryCollegeIdAndName();
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/discipline")
    public JsonResult queryDiscipline() {
        JsonResult jsonResult;
        List<Discipline> data = metaQueryService.queryDiscipline();
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/major")
    public JsonResult queryMajor() {
        JsonResult jsonResult;
        List<Major> data = metaQueryService.queryMajor();
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/round")
    public JsonResult queryRound() {
        JsonResult jsonResult;
        List<Integer> data = metaQueryService.queryRound();
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }
}
