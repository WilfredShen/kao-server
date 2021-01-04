package com.kao.server.util.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kao.server.entity.Admin;
import com.kao.server.entity.User;
import com.kao.server.service.AdminService;
import com.kao.server.service.LoginService;
import com.kao.server.util.accounttype.IsLoggedIn;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.http.HttpUtil;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
import com.kao.server.util.token.TokenVerifier;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@Component
@Aspect
@Order(-1)
public class LoginAspect {


    @Autowired
    private LoginService loginService;

    @Autowired
    private AdminService adminService;

    /**
     * 切点方法
     *
     * @param isLoggedIn 标识登录的注解
     */
    @Pointcut("@annotation(isLoggedIn)")
    public void print(IsLoggedIn isLoggedIn) {
    }

    /**
     * 进行登录验证
     *
     * @param pjp        切入点对象
     * @param isLoggedIn 表示登录的注解
     * @return 切面执行的结果
     */
    @Around(value = "print(isLoggedIn)", argNames = "pjp,isLoggedIn")
    public Object authorityVerify(ProceedingJoinPoint pjp, IsLoggedIn isLoggedIn) {
        try {
            HttpServletRequest request = HttpUtil.getRequest();
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            boolean flag = false;
            if (TokenVerifier.verifyToken(accessToken)) {

                String username = TokenVerifier.getUserNameFromToken(accessToken);
                String password = TokenVerifier.getPasswordFromToken(accessToken);
                //用户持有uid,是用户类型
                if (CookieUtil.findCookie(request.getCookies(), "uid") != null) {
                    User user = loginService.findUserByUsername(username);
                    if (user != null) {
                        String userName = user.getUsername();
                        String passWord = user.getPassword();
                        if (userName.equals(username) && passWord.equals(password)) {
                            flag = true;
                        }
                        System.err.println("user: " + userName + " " + passWord);
                    }
                } else if (CookieUtil.findCookie(request.getCookies(), "adminId") != null) {
                    Admin admin = adminService.findAdminByUsername(username);
                    if (admin != null) {
                        String userName = admin.getUsername();
                        String passWord = admin.getPassword();
                        if (userName.equals(username) && passWord.equals(password)) {
                            flag = true;
                        }
                        System.err.println("admin: " + userName + " " + passWord);
                    }
                }
                //判断token是否属于当前用户
            }
            if (flag) {
                return pjp.proceed();
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNAUTHORIZED_DESC);
            }
        } catch (JWTVerificationException j) {
            j.printStackTrace();
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNKNOWN_ERROR_DESC);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNKNOWN_ERROR, JsonResultStatus.UNKNOWN_ERROR_DESC);
        }
    }
}
