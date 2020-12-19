package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.service.UserService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.interceptor.AccountTypeConstant;
import com.kao.server.util.interceptor.IsLoggedIn;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
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
    private UserService userService;

    @GetMapping("/q/user-info")
    @IsLoggedIn
    public JsonResult getUserMessage(HttpServletRequest request) {

        try {
            Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
            User user = userService.findUserByUserId(uid);
            UserMessage userMessage = null;

            if (user.getAccountType() == null) {
                userMessage = userService.getNotVerifiedUserMessageById(uid);
            } else if (user.getAccountType().equals(AccountTypeConstant.getStudentType())) {
                userMessage = userService.getStudentUserMessageById(uid);
            } else if (user.getAccountType().equals(AccountTypeConstant.getTeacherType())) {
                userMessage = userService.getTutorUserMessageById(uid);
            }

            if (userMessage != null) {
                return ResultFactory.buildSuccessJsonResult(userMessage);
            } else {
                return ResultFactory.buildFailJsonResult();
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }
    }

    @PostMapping("/u/user-info")
    @IsLoggedIn
    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg, HttpServletRequest request) {

        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        String phoneNumber = userMsg.getString("phoneNumber");
        String email = userMsg.getString("email");
        Integer raw = userService.updateUserMsg(phoneNumber, email, uid);
        if (raw != null && raw == 1) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult();
        }
    }
}
