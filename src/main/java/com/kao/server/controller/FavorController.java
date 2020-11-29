package com.kao.server.controller;

import com.kao.server.dto.MajorFavorBase;
import com.kao.server.dto.MajorFavorMessage;
import com.kao.server.dto.NewsFavorMessage;
import com.kao.server.dto.StudentId;
import com.kao.server.dto.TutorFavorBase;
import com.kao.server.dto.TutorFavorMessage;
import com.kao.server.service.FavorService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/favor")
public class FavorController {

    @Autowired
    FavorService favorService;

    @PostMapping("/p/major")
    public JsonResult favorMajor(List<MajorFavorBase> majorList, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        try {
            boolean flag = favorService.favorMajor(studentId.getCid(), studentId.getSid(), majorList);
            if (flag) {
                jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
            } else {
                jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
            }
        } catch (Exception e) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.UNCOMPLETED, JsonResultStatus.UNCOMPLETED_DESC);
        }
        return jsonResult;
    }

    @PostMapping("/p/tutor")
    public JsonResult favorTutor(List<TutorFavorBase> tutorList, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        try {
            boolean flag = favorService.favorTutor(studentId.getCid(), studentId.getSid(), tutorList);
            if (flag) {
                jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, null);
            } else {
                jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
            }
        } catch (Exception e) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.UNCOMPLETED, JsonResultStatus.UNCOMPLETED_DESC);
        }
        return jsonResult;
    }

    @GetMapping("/q/news")
    public JsonResult queryNews(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<NewsFavorMessage> data = favorService.queryNews(studentId.getCid(), studentId.getSid());
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/q/major")
    public JsonResult queryMajor(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<MajorFavorMessage> data = favorService.queryMajor(studentId.getCid(), studentId.getSid());
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }

    @GetMapping("/q/tutor")
    public JsonResult queryTutor(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<TutorFavorMessage> data = favorService.queryTutor(studentId.getCid(), studentId.getSid());
        if (data == null) {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.FAIL, JsonResultStatus.FAIL_DESC);
        } else {
            jsonResult = ResultFactory.buildSuccessJsonResult(JsonResultStatus.SUCCESS_DESC, data);
        }
        return jsonResult;
    }
}
