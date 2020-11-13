package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.controller.ControllerInterfaces.RegisterController;
import com.kao.server.service.BaseServiceImpl;
import com.kao.server.service.UserServiceImpl;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/Visitor")
public class RgisterController implements RegisterController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/register")
    @ResponseBody
    @Override
    public JsonResult<String> register(JSONObject registerMessageJSON, HttpServletRequest request) {
        HttpSession session = request.getSession();
        JsonResult<String> jsonResult = new JsonResult<>();
        String username = registerMessageJSON.getString("username");
        String userName = userService.selectUsernameByname(username);
        if (userName != null) {
            jsonResult.state = 3;
            jsonResult.message = "用户名已存在";
            return jsonResult;
        }
        return jsonResult;
    }

    @RequestMapping("/getVfCode")
    @ResponseBody
    @Override
    public JsonResult<String> getVerificationCode(@RequestBody JSONObject phoneNumberMessageJSON, HttpServletRequest request) {

        HttpSession session = request.getSession();

        String phoneNumber = phoneNumberMessageJSON.getString("phoneNumber");
        //随机生成六位验证码
        String verifyCode = userService.getverifiedCode(phoneNumber);
        JsonResult<String> jsonResult = new JsonResult<>();
        if (verifyCode != null) {
            session.setAttribute("verifyCode", verifyCode);
            jsonResult.state = 0;
            jsonResult.data = verifyCode;
        } else {
            jsonResult.state = 4;
            jsonResult.message = "验证码获取失败!请输入正确的手机号码";
        }
        return jsonResult;
    }
}
