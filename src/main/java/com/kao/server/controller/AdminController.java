package com.kao.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import com.kao.server.service.AdminService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.intercepter.AccountTypeConstant;
import com.kao.server.util.intercepter.IsAdmin;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 全鸿润、沈伟峰
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login(@RequestBody JSONObject adminMsg, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = adminMsg.getString("username");
        String password = adminMsg.getString("password");

        Admin admin = adminService.findUserByUsername(username);
        int state = adminService.handleLogin(admin, username, password);

        JsonResult jsonResult = new JsonResult(null, null, null);

        if (state == JsonResultStatus.SUCCESS) {
            String token = TokenGenerator.generateToken(
                    (admin).getUsername(),
                    String.valueOf(admin.getAdminId()),
                    (admin).getPassword(),
                    AccountTypeConstant.getAdminType()
            );
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            jsonResult.setStatus(state);
            Cookie tokenCookie = new Cookie("accessToken", token);
            Cookie uidCookie = new Cookie("uid", String.valueOf(admin.getAdminId()));
            tokenCookie.setMaxAge(24 * 60 * 60);
            tokenCookie.setDomain("test.com");
            tokenCookie.setPath("/");
            uidCookie.setMaxAge(24 * 60 * 60);
            uidCookie.setPath("/");
            uidCookie.setDomain("test.com");
            response.addCookie(tokenCookie);
            response.addCookie(uidCookie);
        } else if (state == JsonResultStatus.USERNAME_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_WRONG_DESC);
        } else if (state == JsonResultStatus.USERNAME_ISNULL) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_ISNULL_DESC);
        } else if (state == JsonResultStatus.PASSWORD_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_WRONG_DESC);
        } else if (state == JsonResultStatus.PASSWORD_ISNULL) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_ISNULL_DESC);
        }

        return jsonResult;
    }

    @RequestMapping(value = "/get_evaluation", method = RequestMethod.GET)
    @IsLoggedIn
    @IsAdmin
    public JsonResult getEvaluation(@RequestParam() int round) {
        List<AdminViewEvaluation> data = adminService.findEvaluationByRound(round);
        if (data != null) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        }

    }

    @RequestMapping(value = "/upload_evaluation", method = RequestMethod.POST)
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadEvaluations(@RequestBody JSONObject evaluations, HttpServletRequest request) {

        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        JSONArray res = evaluations.getJSONArray("evaluations");
        List<EvaluationBase> results = res.toJavaList(EvaluationBase.class);
        if (results != null) {
            int len = results.size();
            if (len != adminService.uploadEvaluationResult(results, adminId)) {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNCOMPLETED, JsonResultStatus.UNCOMPLETED_DESC);
            }
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        } else {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }

    }

    @RequestMapping(value = "/update_evaluation", method = RequestMethod.POST)
    @IsLoggedIn
    @IsAdmin
    public JsonResult updateEvaluation(@RequestBody JSONObject evaluation, HttpServletRequest request) {

        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        String mid = evaluation.getString("mid");
        String cid = evaluation.getString("cid");
        String result = evaluation.getString("newResult");
        String round = evaluation.getString("round");

        int raws = adminService.updateEvaluationResult(
                cid,
                mid,
                Integer.parseInt(round),
                adminId,
                result
        );

        if (raws == 1) {
            return ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
        }

        return ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);

    }

    @PostMapping("/p/news")
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadNews(NewsBase news, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        if (adminId == null) {
            jsonResult = ResultFactory.buildFailJsonResult("UNAUTHORIZED");
        } else {
            Integer count = adminService.uploadNews(news, adminId);
            if (count != null && count == 1) {
                jsonResult = ResultFactory.buildSuccessJsonResult();
            } else {
                jsonResult = ResultFactory.buildFailJsonResult();
            }
        }
        return jsonResult;
    }

    @GetMapping("/q/news")
    @IsLoggedIn
    @IsAdmin
    public JsonResult queryNews(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        if (adminId == null) {
            jsonResult = ResultFactory.buildFailJsonResult("UNAUTHORIZED");
        } else {
            List<NewsBase> data = adminService.queryNews();
            jsonResult = ResultFactory.listPack(data);
        }
        return jsonResult;
    }

    @PostMapping("/u/news")
    @IsLoggedIn
    @IsAdmin
    public JsonResult updateNews(NewsBase news, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        if (adminId == null) {
            jsonResult = ResultFactory.buildFailJsonResult("UNAUTHORIZED");
        } else {
            Integer count = adminService.updateNews(news, adminId);
            if (count != null && count == 1) {
                jsonResult = ResultFactory.buildSuccessJsonResult();
            } else {
                jsonResult = ResultFactory.buildFailJsonResult();
            }
        }
        return jsonResult;
    }
}
