package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.UserServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.intercepter.IsLoggedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@RequestMapping(value = "/user", method = RequestMethod.GET)
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/get_user_info")
    @IsLoggedIn
    @ResponseBody
    public JsonResult getUserMessage(HttpServletRequest request) {
        return userService.getUserMessage(request);
    }

    @RequestMapping(value = "/update_user_msg", method = RequestMethod.POST)
    @IsLoggedIn
    @ResponseBody
    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg) {
        return userService.updateUserMsg(userMsg);
    }
}
