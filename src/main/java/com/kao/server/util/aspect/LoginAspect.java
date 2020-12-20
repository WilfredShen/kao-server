package com.kao.server.util.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
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
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 全鸿润
 */
@Component
@Aspect
@Order(-1)
public class LoginAspect {

    @Pointcut("@annotation(isLoggedIn)")
    public void print(IsLoggedIn isLoggedIn) {
    }

    @Around(value = "print(isLoggedIn)", argNames = "pjp,isLoggedIn")
    public Object authorityVerify(ProceedingJoinPoint pjp, IsLoggedIn isLoggedIn) {
        try {
            HttpServletRequest request = HttpUtil.getRequest();
            HttpSession session = request.getSession();
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            boolean flag = false;
            if (TokenVerifier.verifyToken(accessToken)) {
                String username = TokenVerifier.getUserNameFromToken(accessToken);
                String password = TokenVerifier.getPasswordFromToken(accessToken);
                String userName = (String) session.getAttribute("username");
                String passWord = (String) session.getAttribute("password");
                System.err.println(userName + " " + passWord);
                //判断token是否属于当前用户
                if (userName.equals(username) && passWord.equals(password)) {
                    flag = true;
                }
            }
            if (flag) {
                Object object = pjp.proceed();
                return object;
            } else {
                return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNAUTHORIZED_DESC);
            }
        } catch (JWTVerificationException j) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNAUTHORIZED, JsonResultStatus.UNKNOWN_ERROR_DESC);
        } catch (Throwable throwable) {
            return ResultFactory.buildFailJsonResult(JsonResultStatus.UNKNOWN_ERROR, JsonResultStatus.UNKNOWN_ERROR_DESC);
        }
    }
}
