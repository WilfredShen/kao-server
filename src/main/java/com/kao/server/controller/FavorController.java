package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.*;
import com.kao.server.service.FavorService;
import com.kao.server.service.StudentService;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.accounttype.IsStudent;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
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
    private FavorService favorService;
    @Autowired
    private StudentService studentService;

    @PostMapping("/p/major")
    @IsLoggedIn
    @IsStudent
    public JsonResult favorMajor(@RequestBody List<MajorFavorBase> majorList, HttpServletRequest request) {
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
    public JsonResult favorTutor(@RequestBody List<TutorFavorBase> tutorList, HttpServletRequest request) {
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

    /**
     * @param jsonObject 接收前端的数据,包括学校代码和专业代码
     * @param request    HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/d/major")
    @IsLoggedIn
    @IsStudent
    public JsonResult deleteMajor(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String majorCid = jsonObject.getString("majorCid");
        String majorMid = jsonObject.getString("majorMid");
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        JsonResult jsonResult;
        Integer raw = favorService.deleteMajor(studentId.getCid(), studentId.getSid(), majorCid, majorMid);
        if (raw != null && raw == 1) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.CANCEL_COLLECTION_FAILED, JsonResultStatus.CANCEL_COLLECTION_FAILED_DESC);
        }

        return jsonResult;
    }

    /**
     * @param jsonObject 接收前端的数据,包括教师所属学校代码和专业代码
     * @param request    HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/d/tutor")
    @IsLoggedIn
    @IsStudent
    public JsonResult deleteTutor(@RequestBody JSONObject jsonObject, HttpServletRequest request) {

        String tutorCid = jsonObject.getString("tutorCid");
        String tutorTid = jsonObject.getString("tutorTid");
        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        StudentId studentId = favorService.getStudentId(uid);
        JsonResult jsonResult;
        Integer raw = favorService.deleteTutor(studentId.getCid(), studentId.getSid(), tutorCid, tutorTid);
        if (raw != null && raw == 1) {
            jsonResult = ResultFactory.buildSuccessJsonResult();
        } else {
            jsonResult = ResultFactory.buildFailJsonResult(JsonResultStatus.CANCEL_COLLECTION_FAILED, JsonResultStatus.CANCEL_COLLECTION_FAILED_DESC);
        }

        return jsonResult;
    }
}
