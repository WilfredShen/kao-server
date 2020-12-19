package com.kao.server.util.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kao.server.dto.StudentMessage;
import com.kao.server.service.StudentService;
import com.kao.server.util.accounttype.IsStudent;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.http.HttpUtil;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.json.ResultFactory;
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
public class StudentAspect {

    @Autowired
    @Qualifier("StudentServiceOfAspect")
    private StudentService studentService;

    @Pointcut("@annotation(isStudent)")
    public void print(IsStudent isStudent) {
    }

    @Around("print(isStudent)")
    public Object authorityVerify(ProceedingJoinPoint proceedingJoinPoint, IsStudent isStudent) {

        try {
            HttpServletRequest request = HttpUtil.getRequest();
            int uid = CookieUtil.parseInt(request.getCookies(), "uid");
            StudentMessage studentMessage = studentService.getStuMsg(uid);
            if (studentMessage != null) {
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