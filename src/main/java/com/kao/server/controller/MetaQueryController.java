package com.kao.server.controller;

import com.kao.server.dto.CollegeIdAndName;
import com.kao.server.dto.RankBase;
import com.kao.server.entity.Discipline;
import com.kao.server.entity.Major;
import com.kao.server.service.MetaQueryService;
import com.kao.server.util.json.JsonResult;
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
    private MetaQueryService metaQueryService;

    /**
     * 查询院校列表
     *
     * @return 请求结果
     */
    @GetMapping("/college")
    public JsonResult queryCollegeIdAndName() {
        JsonResult jsonResult;
        List<CollegeIdAndName> data = metaQueryService.queryCollegeIdAndName();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    /**
     * 查询学科列表
     *
     * @return 请求结果
     */
    @GetMapping("/discipline")
    public JsonResult queryDiscipline() {
        JsonResult jsonResult;
        List<Discipline> data = metaQueryService.queryDiscipline();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    /**
     * 查询专业列表
     *
     * @return 请求结果
     */
    @GetMapping("/major")
    public JsonResult queryMajor() {
        JsonResult jsonResult;
        List<Major> data = metaQueryService.queryMajor();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    /**
     * 查询评估伦次
     *
     * @return 请求结果
     */
    @GetMapping("/round")
    public JsonResult queryRound() {
        JsonResult jsonResult;
        List<Integer> data = metaQueryService.queryRound();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    /**
     * 查询排名列表
     *
     * @return 排名列表
     */
    @GetMapping("/rank")
    public JsonResult queryRank() {
        JsonResult jsonResult;
        List<RankBase> data = metaQueryService.queryRank();
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }
}
