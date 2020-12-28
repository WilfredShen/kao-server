package com.kao.server.util.checker;

import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.DigestGenerator;

/**
 * @author 全鸿润
 */
public class LoginChecker {

    /**
     * @param user     用户对象
     * @param username 用户名
     * @param password 密码
     * @return 验证结果
     */
    public static int checkLogin(Object user, String username, String password) {

        if (user != null) {
            if (user instanceof User) {
                String digest = DigestGenerator.getDigest(password, ((User) user).getSalt());
                System.err.println("checkLogin: " + digest);
                if (!((User) user).getPassword().equals(digest)) {
                    return JsonResultStatus.PASSWORD_WRONG;
                }
            }
            if (user instanceof Admin) {
                if (!((Admin) user).getPassword().equals(password)) {
                    return JsonResultStatus.PASSWORD_WRONG;
                }
            }
            return JsonResultStatus.SUCCESS;
        } else {
            return JsonResultStatus.USERNAME_WRONG;
        }
    }
}
