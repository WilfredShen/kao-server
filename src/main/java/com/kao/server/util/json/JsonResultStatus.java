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
    static public String NEWS_ALREADY_EXISTS_DESC = "新闻已存在";

    static public Integer CANCEL_COLLECTION_FAILED = 2090;
    static public String CANCEL_COLLECTION_FAILED_DESC = "取消收藏失败";

    static public Integer REAL_AUTH_FAILED = 3060;
    static public String REAL_AUTH_FAILED_DESC = "实名认证未通过";

    static public Integer STUDENT_AUTH_FAILED = 3061;
    static public String STUDENT_AUTH_FAILED_DESC = "学生认证未通过";

    static public Integer TUTOR_AUTH_FAILED = 3062;
    static public String TUTOR_AUTH_FAILED_DESC = "导师认证未通过";

    static public Integer UPDATE_PASSWORD_FAILED = 3040;
    static public String UPDATE_PASSWORD_FAILED_DESC = "修改密码失败";

    static public Integer USERNAME_WRONG = 410;
    static public String USERNAME_WRONG_DESC = "用户名错误";

    static public Integer USERNAME_IS_EXITED = 413;
    static public String USERNAME_IS_EXITED_DESC = "用户名已存在";

    static public Integer PASSWORD_WRONG = 414;
    static public String PASSWORD_WRONG_DESC = "密码错误";

    static public Integer PHONE_NUMBER_EXISTED = 421;
    static public String PHONE_NUMBER_EXISTED_DESC = "手机号已经存在";
    static public Integer PHONE_NUMBER_IS_WRONG = 422;
    static public String PHONE_NUMBER_IS_WRONG_DESC = "请输入该账户注册的手机号";

    static public Integer VERIFICATIONS_GET_FAILED = 423;
    static public String VERIFICATIONS_GET_FAILED_DESC = "验证码获取失败";
    static public Integer VERIFICATIONS_IS_WRONG = 425;
    static public String VERIFICATIONS_IS_WRONG_DESC = "验证码输入错误";
}
