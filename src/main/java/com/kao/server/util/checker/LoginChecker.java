package com.kao.server.util.checker;

import com.kao.server.entity.User;
import com.kao.server.service.impl.LoginServiceImpl;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.token.TokenGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 全鸿润
 */
public class LoginChecker {


    public static JsonResult checkLogin(String username, String password, LoginServiceImpl loginService, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (username == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.USERNAME_ISNULL, "用户名不能为空");
        }
        if (password == null) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.PASSWORD_ISNULL, "密码不能为空");
        }
        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);
        User user = loginService.findUserByUsername(username);
        if (user != null && user.getUsername().equals(username)) {
            if (user.getPassword().equals(password)) {
                session.setAttribute("username",username);
                session.setAttribute("password",password);
                String token = TokenGenerator.generateToken(
                        user.getUsername(),
                        String.valueOf(user.getUid()),
                        user.getPassword(),
                        user.getAccountType()
                );
                jsonResult.setStatus(JsonResultStatus.SUCCESS);
                jsonResult.setMessage(null);
                jsonResult.setData(token);
            } else {
                jsonResult.setStatus(JsonResultStatus.PASSWORD_WRONG);
                jsonResult.setMessage("密码错误");
            }
        } else {
            jsonResult.setStatus(JsonResultStatus.USERNAME_WRONG);
            jsonResult.setMessage("用户名错误");
        }
        return jsonResult;
    }
}
