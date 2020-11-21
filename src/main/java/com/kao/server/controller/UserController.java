package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.UserServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.login.IsLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@RequestMapping(value = "/user", method = RequestMethod.GET)
@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/user_info")
    @IsLoggedIn
    @ResponseBody
    public JsonResult getUserMessage(HttpServletRequest request) {
        return userService.getUserMessage(request);
    }

    @RequestMapping(value = "/updateUserMsg", method = RequestMethod.POST)
    @IsLoggedIn
    @ResponseBody
    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg) {
        return userService.updateUserMsg(userMsg);
    }
}
