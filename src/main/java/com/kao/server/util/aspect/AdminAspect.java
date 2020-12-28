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

    /**
     * 切点方法
     *
     * @param isAdmin 标识管理员的注解
     */
    @Pointcut("@annotation(isAdmin)")
    public void print(IsAdmin isAdmin) {
    }

    /**
     * 切面方法,处理管理员权限验证
     *
     * @param proceedingJoinPoint 切入点对象
     * @param isAdmin             标识管理员的注解
     * @return 切面执行的结果
     */
    @Around(value = "print(isAdmin)", argNames = "proceedingJoinPoint,isAdmin")
    public Object authorityVerify(ProceedingJoinPoint proceedingJoinPoint, IsAdmin isAdmin) {

        try {
            HttpServletRequest request = HttpUtil.getRequest();
            String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
            Admin admin = adminService.findAdminByUsername(TokenVerifier.getUserNameFromToken(accessToken));
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
