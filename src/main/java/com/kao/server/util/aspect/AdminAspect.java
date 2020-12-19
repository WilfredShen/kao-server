package com.kao.server.util.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kao.server.entity.Admin;
import com.kao.server.service.AdminService;
import com.kao.server.util.accounttype.IsAdmin;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 全鸿润
 */
@Component
@Aspect
@Order(0)
public class AdminAspect {

    @Autowired
    @Qualifier("AdminServiceOfAspect")
    private AdminService adminService;

    @Pointcut("@annotation(isAdmin)")
    public void print(IsAdmin isAdmin) {
    }

    @Around("print(isAdmin)")
    public Object authorityVerify(ProceedingJoinPoint proceedingJoinPoint, IsAdmin isAdmin) {

        try {
            HttpServletRequest request = HttpUtil.getRequest();
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            Admin admin = adminService.findUserByUsername(TokenVerifier.getUserNameFromToken(accessToken));
            if (admin != null) {
                return proceedingJoinPoint.proceed();
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
