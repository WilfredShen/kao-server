package com.kao.server.util.checker;

import com.kao.server.entity.User;
import com.kao.server.service.UserService;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.http.HttpUtil;
import com.kao.server.util.interceptor.AccountTypeConstant;
import com.kao.server.util.interceptor.IsStudent;
import com.kao.server.util.interceptor.IsTutor;
import com.kao.server.util.token.TokenVerifier;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author 全鸿润
 */
@Component
public class UserChecker {

    public static boolean check(Method method, UserService userService) {

        HttpServletRequest request;
        HttpSession session;
        try {
            request = HttpUtil.getRequest();
            session = request.getSession();
        } catch (Exception e) {
            return false;
        }
        try {
            int uid = CookieUtil.parseInt(request.getCookies(), "uid");
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            //先判断token是否有效
            if (TokenVerifier.verifyToken(accessToken)) {
                String username = TokenVerifier.getUserNameFromToken(accessToken);
                String password = TokenVerifier.getPasswordFromToken(accessToken);
                String userName = (String) session.getAttribute("username");
                String passWord = (String) session.getAttribute("password");
                System.err.println(userName + " " + passWord);
                //判断token是否属于当前用户
                if (!userName.equals(username) || !passWord.equals(password)) {
                    return false;
                } else {
                    //判断权限
                    User user = userService.findUserByUserId(uid);
                    String accountType = user.getAccountType();
                    if (method.getAnnotation(IsStudent.class) != null && !AccountTypeConstant.getStudentType().equals(accountType)) {
                        System.err.println("验证学生身份中......");
                        return false;
                    } else if (method.getAnnotation(IsTutor.class) != null && !AccountTypeConstant.getTeacherType().equals(accountType)) {
                        System.err.println("验证老师身份中......");
                        return false;
                    } else {
                        System.err.println("验证通过!");
                        return true;
                    }
                }
            } else {
                return false;
            }
        } catch (NullPointerException e) {
            System.err.println("请检查uid或token是否为空或者token失效");
            throw e;
        }

    }
}