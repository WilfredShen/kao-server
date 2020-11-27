package com.kao.server.util.checker;

import com.kao.server.dto.TutorMessage;
import com.kao.server.service.impl.TutorServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

/**
 * @author 全鸿润
 */
public class TutorMsgChecker {

    public static JsonResult checkTutorMsg(String phone, String email, String college, String major, TutorServiceImpl tutorService, String research, String uid) {
        try {
            int id = Integer.parseInt(uid);
            if (tutorService.findTutorById(id) == null) {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED_USER, "越权访问");
            }

            if (phone != null) {
                if (tutorService.updatePhone(id, phone) == 1) {
                    System.err.println("YES");
                }
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空!");
            }

            if (college != null) {
                if (tutorService.updateCollege(id, college) == 1) {
                    System.err.println("YES");
                }
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.COLLEGE_ISNULL, "学校不能为空!");
            }

            if (email != null) {
                tutorService.updateEmail(id, email);
            }

            if (major != null) {
                tutorService.updateMajor(id, major);
            }
            if (research != null) {
                tutorService.updateResearch(id, research);
            }
            TutorMessage tutorMessage = tutorService.findTutorById(id);
            return ResultFactory.buildSuccessJsonResult("修改成功", tutorMessage);
        } catch (Exception e) {

            System.err.println("请检查uid格式是否正确");
        }

        return ResultFactory.buildFailJsonResult(JsonResultStatus.UNKNOWN_ERROR, "未知错误!");
    }

}
