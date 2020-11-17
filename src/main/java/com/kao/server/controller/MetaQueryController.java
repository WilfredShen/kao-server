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
        List<CollegeIdAndName> data = metaQueryService.queryCollegeIdAndName();
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, "query failed");
        } else {
            return ResultFactory.buildSuccessJsonResult("query success", data);
        }
    }

    @GetMapping("/discipline")
    public JsonResult queryDiscipline() {
        List<Discipline> data = metaQueryService.queryDiscipline();
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, "query failed");
        } else {
            return ResultFactory.buildSuccessJsonResult("query success", data);
        }
    }

    @GetMapping("/major")
    public JsonResult queryMajor() {
        List<Major> data = metaQueryService.queryMajor();
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, "query failed");
        } else {
            return ResultFactory.buildSuccessJsonResult("query success", data);
        }
    }

    @GetMapping("/round")
    public JsonResult queryRound() {
        List<Integer> data = metaQueryService.queryRound();
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, "query failed");
        } else {
            return ResultFactory.buildSuccessJsonResult("query success", data);
        }
    }
}
