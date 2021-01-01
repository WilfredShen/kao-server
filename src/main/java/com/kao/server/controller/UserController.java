package com.kao.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.dto.UserMessage;
import com.kao.server.entity.User;
import com.kao.server.service.UserService;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.cookie.CookieUtil;
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

    /**
     * 查询用户信息
     *
     * @param request HttpServletRequest
     * @return 用户信息
     */
    @GetMapping("/q/user-info")
    @IsLoggedIn
    public JsonResult getUserMessage(HttpServletRequest request) {

        try {
            Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
            User user = userService.findUserByUserId(uid);
            UserMessage userMessage = null;

            if (user.getName() != null && user.getIdentity() != null) {
                userMessage = userService.getVerifiedUserMessageById(uid);
                System.err.println("已认证");
            } else {
                userMessage = userService.getNotVerifiedUserMessageById(uid);
                System.err.println("没认证");
            }
            System.err.println(userMessage);

            if (userMessage != null) {
                return ResultFactory.buildSuccessJsonResult(userMessage);
            } else {
                return ResultFactory.buildFailJsonResult();
            }
        } catch (Exception e) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.ILLEGAL_PARAM, JsonResultStatus.ILLEGAL_PARAM_DESC);
        }
    }

    /**
     * 修改用户信息
     *
     * @param userMsg 修改过的用户信息
     * @param request HttpServletRequest
     * @return 封装的Json数据
     */
    @PostMapping("/u/user-info")
    @IsLoggedIn
    public JsonResult updateUserMsg(@RequestBody JSONObject userMsg, HttpServletRequest request) {

        Integer uid = CookieUtil.parseInt(request.getCookies(), "uid");
        String phoneNumber = userMsg.getString("phoneNumber");
        String email = userMsg.getString("email");
        Integer row = userService.updateUserMsg(phoneNumber, email, uid);
        if (row != null && row == 1) {
            return ResultFactory.buildSuccessJsonResult();
        } else {
            return ResultFactory.buildFailJsonResult();
        }
    }
}
