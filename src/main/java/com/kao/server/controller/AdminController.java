package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.AdminViewEvaluation;
import com.kao.server.dto.EvaluationBase;
import com.kao.server.dto.NewsBase;
import com.kao.server.entity.Admin;
import com.kao.server.service.AdminService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.interceptor.IsAdmin;
import com.kao.server.util.interceptor.IsLoggedIn;
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
    private AdminService adminService;

    @PostMapping("/login")
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
                    (admin).getPassword()
            );
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            jsonResult.setStatus(state);
            Cookie tokenCookie = CookieUtil.buildCookie("accessToken", token);
            Cookie adminIdCookie = CookieUtil.buildCookie("adminId", String.valueOf(admin.getAdminId()));
            response.addCookie(tokenCookie);
            response.addCookie(adminIdCookie);
        } else if (state == JsonResultStatus.USERNAME_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_WRONG_DESC);
        } else if (state == JsonResultStatus.PASSWORD_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_WRONG_DESC);
        }

        return jsonResult;
    }

    @GetMapping("/get_evaluation")
    @IsLoggedIn
    @IsAdmin
    public JsonResult getEvaluation(@RequestParam("round") int round,
                                    @RequestParam("major") String major,
                                    @RequestParam("college") String college) {
        JsonResult jsonResult;
        List<AdminViewEvaluation> data = adminService.findEvaluationByRound(round, major, college);
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @PostMapping("/upload_evaluation")
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadEvaluations(@RequestBody EvaluationBase results, HttpServletRequest request) {

        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        Integer raws = adminService.uploadEvaluationResult(results, adminId);
        if (raws == null || raws != 1) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNCOMPLETED, JsonResultStatus.UNCOMPLETED_DESC);
        }
        return ResultFactory.buildSuccessJsonResult();

    }

    @PostMapping("/update_evaluation")
    @IsLoggedIn
    @IsAdmin
    public JsonResult updateEvaluation(@RequestBody JSONObject evaluation, HttpServletRequest request) {

        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        String mid = evaluation.getString("mid");
        String cid = evaluation.getString("cid");
        String result = evaluation.getString("newResult");
        String round = evaluation.getString("round");

        JsonResult jsonResult;

        Integer raws = adminService.updateEvaluationResult(
                cid,
                mid,
                Integer.parseInt(round),
                adminId,
                result
        );

        if (raws!=null && raws == 1) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }

        return jsonResult;

    }

    @PostMapping("/p/news")
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadNews(@RequestBody NewsBase news, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer adminId = CookieUtil.parseInt(request.getCookies(), "adminId");
        System.out.println();
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
    public JsonResult updateNews(@RequestBody NewsBase news, HttpServletRequest request) {
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
