package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.controller.ControllerInterfaces.UserLoginController;
import com.kao.server.entity.User;
import com.kao.server.service.BaseServiceImpl;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/Visitor")
public class LoginController implements UserLoginController {

    @Autowired
    BaseServiceImpl baseServiceImpl;

    @RequestMapping(value = "/register")
    @ResponseBody
    @Override
    public JsonResult<String> register(@RequestBody JSONObject registerMessageJSON, HttpServletRequest request) {
        HttpSession session = request.getSession();
        JsonResult<String> jsonResult = new JsonResult<>();
        String username = registerMessageJSON.getString("username");
        String userName = baseServiceImpl.selectUsernameByname(username);
        if (userName != null) {
            jsonResult.state = 3;
            jsonResult.message = "用户名已存在";
            return jsonResult;
        }
        return jsonResult;
    }

    @RequestMapping("/login")
    @ResponseBody
    @Override
    public JsonResult<String> login(@RequestBody JSONObject loginMessageJSON, HttpServletRequest request) {

        HttpSession session = request.getSession();
        JsonResult<String> jsonResult = new JsonResult<>();
        String username = loginMessageJSON.getString("username");
        String password = loginMessageJSON.getString("password");
        if (session.getAttribute("username") != null && session.getAttribute("password") != null) {
            if (session.getAttribute("username").equals(username) && session.getAttribute("password").equals(password)) {
                jsonResult.state = 0;
                return jsonResult;
            }
        }
        User user = baseServiceImpl.selectPasswordByUserName(username);
        System.err.println(user.getUsername());
        System.err.println(user.getPassword());
        if (user != null && user.getUsername().equals(username)) {
            if (user.getPassword().equals(password)) {
                jsonResult.state = 0;
                session.setAttribute("username", username);
                session.setAttribute("password", password);
            } else {
                jsonResult.state = 2;
                jsonResult.message = "密码输入错误";
            }
        } else {
            jsonResult.state = 1;
            jsonResult.message = "用户名输入错误";
        }

        return jsonResult;
    }

    @RequestMapping("/forgetPassword")
    @ResponseBody
    @Override
    public JsonResult<String> forgetPassword(@RequestBody JSONObject passwordagainMessageJSON, HttpServletRequest request) {
        HttpSession session = request.getSession();
        JsonResult<String> jsonResult = new JsonResult<>();
        String username = passwordagainMessageJSON.getString("username");
        String password = passwordagainMessageJSON.getString("password");
        if (baseServiceImpl.updatePassword(username, password) > 0) {
            jsonResult.state = 0;
        } else {
            jsonResult.state = 6;
            jsonResult.message = "修改密码失败";
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
        String verifyCode = baseServiceImpl.getverifiedCode(phoneNumber);
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
