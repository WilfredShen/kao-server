package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.Admin;
import com.kao.server.service.AdminServiceImpl;
import com.kao.server.service.BaseServiceImpl;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequestMapping("/admin")
public class AdminController {

    @Autowired
    BaseServiceImpl baseServiceImpl;
    @Autowired
    AdminServiceImpl adminService;

    @ResponseBody
    @RequestMapping("/login")
    public JsonResult<Void> login(JSONObject loginMessageJSON, HttpServletRequest request) {

        HttpSession session = request.getSession();
        JsonResult<Void> jsonResult = new JsonResult<>();
        String username = loginMessageJSON.getString("username");
        String password = loginMessageJSON.getString("password");
        if (session.getAttribute("username") != null && session.getAttribute("password") != null) {
            if (session.getAttribute("username").equals(username) && session.getAttribute("password").equals(password)) {
                jsonResult.state = 0;
                return jsonResult;
            }
        }
        Admin admin = adminService.selectAdmindByUserName(username);
        if (admin != null && admin.getUsername().equals(username)) {
            if (admin.getPassword().equals(password)) {
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

}
