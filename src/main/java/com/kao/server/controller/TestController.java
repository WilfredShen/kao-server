package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.entity.UserTest;
import com.kao.server.service.TestService;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */

@RestController
@RequestMapping(value ="/test")
public class TestController {

    @Autowired
    private TestService testService;
    @PostMapping(value ="/add")
    public JsonResult addTest(@RequestBody JSONObject jsonObject){

        String uid = jsonObject.getString("uid");
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        UserTest userTest = new UserTest();
        userTest.setUid(Integer.parseInt(uid));
        userTest.setPassword(password);
        userTest.setUsername(username);
        Integer raw = testService.addNewUser(userTest);
        if ( raw!=null &&raw == 1){
            return ResultFactory.buildSuccessJsonResult();
        }else{
            return ResultFactory.buildFailJsonResult();
        }

    }

    @GetMapping(value ="/find")
    public JsonResult findUser(HttpServletRequest request){

       UserTest userTest= testService.findUserById(Integer.valueOf(request.getParameter("uid")));

       JsonResult jsonResult = new JsonResult(null,null,null);
       jsonResult.setData(userTest);

       return jsonResult;

    }
}
