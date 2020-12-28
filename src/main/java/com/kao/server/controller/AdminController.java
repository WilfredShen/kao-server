package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.*;
import com.kao.server.entity.Admin;
import com.kao.server.service.AdminService;
import com.kao.server.util.accounttype.IsAdmin;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.image.QiniuCloudUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.security.SecurityUtil;
import com.kao.server.util.token.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param adminMsg 修改的管理员信息
     * @param request  HttpRequest
     * @param response HttpResponse
     * @return 封装的Json数据
     */
    @PostMapping("/login")
    public JsonResult login(@RequestBody JSONObject adminMsg, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String username = adminMsg.getString("username");
        String password = adminMsg.getString("password");

        int state = adminService.handleLogin(username, password);
        JsonResult jsonResult = new JsonResult(null, null, null);
        if (state == JsonResultStatus.SUCCESS) {
            String key = "admin" + username;
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            Admin admin = (Admin) operations.get(key);
            if (admin != null) {
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
            }
        } else if (state == JsonResultStatus.USERNAME_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.USERNAME_WRONG_DESC);
        } else if (state == JsonResultStatus.PASSWORD_WRONG) {
            return ResultFactory.buildFailJsonResult(state, JsonResultStatus.PASSWORD_WRONG_DESC);
        }

        return jsonResult;
    }

    /**
     * 查询评估结果
     *
     * @param round   评估轮次
     * @param major   专业
     * @param college 学校
     * @return 封装的Json数据
     */
    @GetMapping("/q/evaluation")
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

    /**
     * 上传评估结果
     *
     * @param results 评估结果信息
     * @param request HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/p/evaluation")
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

    /**
     * 修改评估结果
     *
     * @param evaluation 修改的评估结果
     * @param request    HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/u/evaluation")
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

        if (raws != null && raws == 1) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildFailJsonResult();
        }

        return jsonResult;

    }

    /**
     * 上传新闻
     *
     * @param news    新闻
     * @param request HTTP 请求
     * @return 请求结果
     */
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

    /**
     * 查询新闻
     *
     * @param request HTTP 请求
     * @return 请求结果
     */
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

    /**
     * 更新新闻
     *
     * @param news    新闻
     * @param request HTTP 请求
     * @return 请求结果
     */
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

    /**
     * 上传图片
     *
     * @param image 图片
     * @return 请求结果
     */
    @PostMapping("/p/image")
    @IsLoggedIn
    @IsAdmin
    public JsonResult uploadImage(MultipartFile image) {
        JsonResult jsonResult;
        try {
            byte[] bytes = image.getBytes();
            String key = SecurityUtil.encodeBase64(SecurityUtil.sha256(bytes));
            String filename = QiniuCloudUtil.upload(image.getBytes(), key);
            String url = QiniuCloudUtil.packUrl(filename);
            jsonResult = ResultFactory.buildSuccessJsonResult(url);
        } catch (IOException e) {
            e.printStackTrace();
            jsonResult = ResultFactory.buildFailJsonResult();
        }
        return jsonResult;
    }

    @GetMapping("/q/userList")
    @IsLoggedIn
    @IsAdmin
    public JsonResult getUserList() {

        List<UserMessageByAdmin> data = adminService.getUserMessageList();
        return ResultFactory.listPack(data);
    }

    @PostMapping("/d/user")
    @IsLoggedIn
    @IsAdmin
    public JsonResult deleteUser(@RequestBody JSONObject jsonObject) {

        try {
            Integer uid = Integer.valueOf(jsonObject.getString("uid"));
            Integer raw = adminService.deleteUser(uid);
            if (raw != null && raw == 1) {
                return ResultFactory.buildSuccessJsonResult();
            } else {
                return ResultFactory.buildFailJsonResult();
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult();
        }
    }

    @PostMapping("/u/user")
    @IsLoggedIn
    @IsAdmin
    public JsonResult updateUser(@RequestBody UpdatedUserMessage updatedUserMessage) {

        Integer raw = adminService.updateUser(updatedUserMessage);
        if (raw != null && raw == 1) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult();
        }
    }

}
