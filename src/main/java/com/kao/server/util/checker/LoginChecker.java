package com.kao.server.util.checker;

import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.util.intercepter.AccountTypeConstant;
import com.kao.server.util.json.JsonResult;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.token.TokenGenerator;

/**
 * @author 全鸿润
 */
public class LoginChecker {


    public static JsonResult checkLogin(Object user, String username, String password) {

        JsonResult jsonResult = ResultFactory.buildJsonResult(null, null, null);
        if (user != null) {
            if (user instanceof User) {
                if (((User) user).getPassword().equals(password)) {
                    String token = TokenGenerator.generateToken(
                            ((User) user).getUsername(),
                            String.valueOf(((User) user).getUid()),
                            ((User) user).getPassword(),
                            ((User) user).getAccountType()
                    );
                    jsonResult.setStatus(JsonResultStatus.SUCCESS);
                    jsonResult.setMessage(null);
                    jsonResult.setData(token);
                } else {
                    jsonResult.setStatus(JsonResultStatus.PASSWORD_WRONG);
                    jsonResult.setMessage("密码错误");
                }
            } else if (user instanceof Admin) {
                System.err.println("check:"+username+password);
                System.err.println("checkAdmin:"+((Admin) user).getUsername()+((Admin) user).getPassword());
                if (((Admin) user).getPassword().equals(password)) {
                    String token = TokenGenerator.generateToken(
                            ((Admin) user).getUsername(),
                            String.valueOf(((Admin) user).getAdminId()),
                            ((Admin) user).getPassword(),
                            AccountTypeConstant.getAdminType()
                    );
                    jsonResult.setStatus(JsonResultStatus.SUCCESS);
                    jsonResult.setMessage(null);
                    jsonResult.setData(token);
                } else {
                    jsonResult.setStatus(JsonResultStatus.PASSWORD_WRONG);
                    jsonResult.setMessage("密码错误");
                }
            }
        } else {
            jsonResult.setStatus(JsonResultStatus.USERNAME_WRONG);
            jsonResult.setMessage("用户名错误");
        }
        return jsonResult;

    }
}
