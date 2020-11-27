package com.kao.server.util.checker;

import com.kao.server.dto.StudentMessage;
import com.kao.server.service.impl.StudentServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;

import java.sql.Date;

/**
 * @author 全鸿润
 */
public class StuMsgChecker {

    public static JsonResult checkStuMsg(String phone, String email, String college, String major, String graduationDate, String expectedMajor,
                                         String queryable, String id, StudentServiceImpl studentService) {
        try {
            int uid = Integer.parseInt(id);
            if (studentService.getstuMsg(uid) == null) {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED_USER, "越权访问");
            }

            if (phone != null) {
                if (studentService.updatePhone(uid, phone) == 1) {
                    System.err.println("YES");
                }
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.PHONENUMBER_ISNULL, "手机号不能为空!");
            }

            if (college != null) {
                if (studentService.updateCollege(uid, college) == 1) {
                    System.err.println("YES");
                }
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.COLLEGE_ISNULL, "学校不能为空!");
            }

            if (graduationDate != null) {
                if (studentService.updateGraduateDate(uid, Date.valueOf(graduationDate)) == 1) {
                    System.err.println("YES");
                }
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.GRADUATEDATE_ISNULL, "毕业日期不能为空!");
            }

            if (email != null) {
                studentService.updateEmail(uid, email);
            }

            if (major != null) {
                studentService.updateMajor(uid, major);
            }

            if (expectedMajor != null) {
                studentService.updateExpectedMajor(uid, expectedMajor);
            }

            if (queryable != null) {
                studentService.updateQueryable(uid, Boolean.parseBoolean(queryable));
            }

            StudentMessage studentMessage = studentService.getstuMsg(uid);
            return ResultFactory.buildSuccessJsonResult("修改成功", studentMessage);
        } catch (Exception e) {

            System.err.println("请检查uid格式是否正确");
        }

        return ResultFactory.buildFailJsonResult(JsonResultStatus.UNKOWN_ERROR, "未知错误!");
    }

}
