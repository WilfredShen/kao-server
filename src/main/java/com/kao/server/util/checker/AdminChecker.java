package com.kao.server.util.checker;

import com.kao.server.entity.Admin;
import com.kao.server.service.AdminService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.token.TokenVerifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.kao.server.util.http.HttpUtil.getRequest;

/**
 * @author 全鸿润
 */
@Component
public class AdminChecker {

    public static boolean check(AdminService adminService) {

        HttpServletRequest request = null;
        HttpSession session = null;
        try {
            request = getRequest();
            session = request.getSession();
        } catch (Exception e) {
            return false;
        }
        try {
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            Admin admin = adminService.findUserByUsername(TokenVerifier.getUserNameFromToken(accessToken));
            if (admin != null) {
                System.err.println("验证管理员身份中......");
                String userName = (String) session.getAttribute("username");
                String passWord = (String) session.getAttribute("password");
                String username = TokenVerifier.getUserNameFromToken(accessToken);
                String password = TokenVerifier.getPasswordFromToken(accessToken);
                //判断token是否属于当前用户
                return userName.equals(username) && passWord.equals(password);
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            System.err.println("token失效或token为空");
            throw e;
        }
    }
}
