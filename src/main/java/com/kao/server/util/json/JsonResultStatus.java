package com.kao.server.util.json;

/**
 * @author 沈伟峰
 */
public class JsonResultStatus {
    static public Integer SUCCESS = 200;
    static public Integer USERNAMEWRONG = 400;
    static public Integer USERNAMEISNULL = 403;
    static public Integer PASSWORDWRONG = 406;
    static public Integer PASSWORDISNULL = 409;
    static public Integer PHONENUMBERISNULL = 412;
    static public Integer PHONENUMBERISEXITED = 413;
    static public Integer PHONENUMBERWRONG = 415;
    static public Integer VERIFICATIONCODEISNULL = 418;
    static public Integer VERIFICATIONCODEWRONG = 421;
    static public Integer VERIFICATIONCODEGETFAILED = 423;
    static public Integer USERNAMEISEXITED = 424;
    static public Integer PASSWORDISNOTTHESAME = 427;
    static public Integer UPDATEPASSWORDFAILED = 430;

}
