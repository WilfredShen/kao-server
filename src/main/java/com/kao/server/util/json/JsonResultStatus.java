package com.kao.server.util.json;

/**
 * @author 沈伟峰
 */
public class JsonResultStatus {

    static public Integer SUCCESS = 200;
    static public String SUCCESS_DESC = "success";

    static public Integer FAIL = 400;
    static public String FAIL_DESC = "fail";

    static public Integer UNAUTHORIZED = 401;
    static public String UNAUTHORIZED_DESC = "unauthorized";

    static public Integer ILLEGAL_PARAM = 402;
    static public String ILLEGAL_PARAM_DESC = "illegal parameters";

    static public Integer NOT_FOUND = 403;
    static public String NOT_FOUND_DESC = "not found";

    static public Integer UNKNOWN_ERROR = 404;
    static public String UNKNOWN_ERROR_DESC = "unknown error";

    static public Integer UNCOMPLETED = 1090;
    static public String UNCOMPLETED_DESC = "未全部完成";

    static public Integer NEWS_ALREADY_EXISTS = 1110;
    static public String  NEWS_ALREADY_EXISTS_DESC = "新闻已存在";

    static public Integer REAL_AUTH_FAILED = 3060;
    static public String REAL_AUTH_FAILED_DESC = "实名认证未通过";

    static public Integer STUDENT_AUTH_FAILED = 3061;
    static public String STUDENT_AUTH_FAILED_DESC = "学生认证未通过";

    static public Integer TUTOR_AUTH_FAILED = 3062;
    static public String TUTOR_AUTH_FAILED_DESC = "导师认证未通过";

    static public Integer USERNAME_WRONG = 401;
    static public Integer USERNAME_ISNULL = 403;
    static public Integer BAD_REQUEST = 404;
    static public Integer PASSWORD_WRONG = 406;
    static public Integer PASSWORD_ISNULL = 409;
    static public Integer PHONENUMBER_ISNULL = 412;
    static public Integer PHONENUMBER_ISEXITED = 413;
    static public Integer PHONENUMBERWRONG = 415;
    static public Integer VERIFICATIONCODE_ISNULL = 418;
    static public Integer VERIFICATIONCOD_EWRONG = 421;
    static public Integer VERIFICATIONCODE_GET_FAILED = 423;
    static public Integer USERNAME_ISEXITED = 424;
    static public Integer PASSWORD_ISNOT_THESAME = 427;
    static public Integer UPDATE_PASSWORD_FAILED = 430;
    static public Integer UNAUTHORIZED_USER = 440;
    static public Integer COLLEGE_ISNULL = 456;
    static public Integer GRADUATEDATE_ISNULL = 453;
}
