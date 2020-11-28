package com.kao.server.controller;

import com.kao.server.dto.NewsBase;
import com.kao.server.service.AdminService;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/p/news")
    public JsonResult uploadNews(NewsBase news, HttpServletRequest request) {
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        int result = adminService.uploadNews(news, adminId);
        if (result == 1) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
    }

    @PostMapping("/q/news")
    public JsonResult queryNews() {
        List<NewsBase> data = adminService.queryNews();
        if (data == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else if (data.size() == 0) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.NOT_FOUND, JsonResultStatus.NOT_FOUND_DESC);
        } else {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
    }

    @PostMapping("/u/news")
    public JsonResult updateNews(NewsBase news, HttpServletRequest request) {
        int adminId = Integer.parseInt(request.getParameter("adminId"));
        int result = adminService.updateNews(news, adminId);
        if (result == 1) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }
    }
}
