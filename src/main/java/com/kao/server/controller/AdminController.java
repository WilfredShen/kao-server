package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.AdminServiceImpl;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 全鸿润
 */
@CrossOrigin
@RestController
@RequestMapping(value ="/admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;


    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult login(@RequestBody JSONObject adminMsg){

        String username = adminMsg.getString("username");
        String password = adminMsg.getString("password");
        System.err.println("Login:"+username+password);
        return adminService.handleLogin(username,password);
    }

}
