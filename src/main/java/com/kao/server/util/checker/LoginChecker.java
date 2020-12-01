package com.kao.server.util.checker;

import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.DigestGenerator;

/**
 * @author 全鸿润
 */
public class LoginChecker {


    public static int checkLogin(Object user, String username, String password) {

        if (user != null) {
            if (user instanceof User) {
                String digest = DigestGenerator.getDigest(password, ((User) user).getSalt());
                if (!((User) user).getPassword().equals(digest)) {
                    return JsonResultStatus.PASSWORD_WRONG;
                }
            }
            if (user instanceof Admin) {
                String digest = DigestGenerator.getDigest(password, ((Admin) user).getSalt());
                if (!((Admin) user).getPassword().equals(digest)) {
                    return JsonResultStatus.PASSWORD_WRONG;
                }
            }
            return JsonResultStatus.SUCCESS;
        } else {
            return JsonResultStatus.USERNAME_WRONG;
        }
    }
}
