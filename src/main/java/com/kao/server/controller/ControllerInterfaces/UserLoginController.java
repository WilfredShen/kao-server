package com.kao.server.controller.ControllerInterfaces;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.json.JsonResult;

import javax.servlet.http.HttpServletRequest;

public interface UserLoginController {

//    //注册
//    public JsonResult<String> register(JSONObject registerMessageJSON, HttpServletRequest request);
    //登录
    public JsonResult<String> login(JSONObject loginMessageJSON,HttpServletRequest request);
    //忘记密码
    public JsonResult<String> forgetPassword(JSONObject passwordagainMessageJSON,HttpServletRequest request);
}
