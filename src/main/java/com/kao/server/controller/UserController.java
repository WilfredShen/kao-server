package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.UserServiceImpl;
import com.kao.server.util.intercepter.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
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

        String uid = request.getParameter("uid");
        return userService.getUserMessage(uid);
    }

    @RequestMapping(value = "/update_user_msg", method = RequestMethod.POST)
    @IsLoggedIn
    @ResponseBody
    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg) {
        String phoneNumber = userMsg.getString("phoneNumber");
        String email = userMsg.getString("email");
        String accountType = userMsg.getString("accountType");
        String uid = userMsg.getString("uid");
        return userService.updateUserMsg(phoneNumber,email,accountType,uid);
    }
}
