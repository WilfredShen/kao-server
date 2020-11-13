package com.kao.server.controller.ControllerInterfaces;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.json.JsonResult;

import javax.servlet.http.HttpServletRequest;

public interface RegisterController {

    //注册
    public JsonResult<String> register(JSONObject registerMessageJSON, HttpServletRequest request);
    //获取验证码
    public JsonResult<String> getVerificationCode(JSONObject phoneNumberMessageJSON , HttpServletRequest request);
}
