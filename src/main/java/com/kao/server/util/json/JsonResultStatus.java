package com.kao.server.util.json;

/**
 * @author 沈伟峰
 */
public class JsonResultStatus {

    static public Integer SUCCESS = 200;
    static public String SUCCESS_DESC = "success";

    static public Integer FAIL = 400;
    static public String FAIL_DESC = "unknown error";

    static public Integer UNKOWN_ERROR = 400;
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

    static public Integer ILLEGAL_PARAM = 450;
    static public String ILLEGAL_PARAM_DESC = "非法参数";
}
