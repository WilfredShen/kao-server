package com.kao.server.controller;

import com.kao.server.dto.*;
import com.kao.server.service.FavorService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.intercepter.IsStudent;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @IsLoggedIn
    @IsStudent
    public JsonResult favorMajor(List<MajorFavorBase> majorList, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        Integer count = favorService.favorMajor(studentId.getCid(), studentId.getSid(), majorList);
        if (count == null) {
            jsonResult = ResultFactory.buildFailJsonResult();
        } else if (count == majorList.size()) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildWarnJsonResult("UNCOMPLETED");
        }
        return jsonResult;
    }

    @PostMapping("/p/tutor")
    @IsLoggedIn
    @IsStudent
    public JsonResult favorTutor(List<TutorFavorBase> tutorList, HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        Integer count = favorService.favorTutor(studentId.getCid(), studentId.getSid(), tutorList);
        if (count == null) {
            jsonResult = ResultFactory.buildFailJsonResult();
        } else if (count == tutorList.size()) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildWarnJsonResult("UNCOMPLETED");
        }
        return jsonResult;
    }

    @GetMapping("/q/news")
    @IsLoggedIn
    @IsStudent
    public JsonResult queryNews(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<NewsFavorMessage> data = favorService.queryNews(studentId.getCid(), studentId.getSid());
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/q/major")
    @IsLoggedIn
    @IsStudent
    public JsonResult queryMajor(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<MajorFavorMessage> data = favorService.queryMajor(studentId.getCid(), studentId.getSid());
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }

    @GetMapping("/q/tutor")
    @IsLoggedIn
    @IsStudent
    public JsonResult queryTutor(HttpServletRequest request) {
        JsonResult jsonResult;
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        List<TutorFavorMessage> data = favorService.queryTutor(studentId.getCid(), studentId.getSid());
        jsonResult = ResultFactory.listPack(data);
        return jsonResult;
    }
}
