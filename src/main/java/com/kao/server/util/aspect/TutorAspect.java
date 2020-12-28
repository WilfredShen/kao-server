package com.kao.server.util.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kao.server.dto.TutorMessage;
import com.kao.server.service.TutorService;
import com.kao.server.util.accounttype.IsTutor;
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
public class TutorAspect {

    @Autowired
    @Qualifier("TutorServiceOfAspect")
    private TutorService tutorService;

    /**
     * 切点方法
     *
     * @param isTutor 标识学生用户权限的注解
     */
    @Pointcut("@annotation(isTutor)")
    public void print(IsTutor isTutor) {
    }

    /**
     * 教师身份验证
     *
     * @param proceedingJoinPoint 切入点对象
     * @param isTutor             表示教师用户的注解
     * @return 切面执行的结果
     */
    @Around(value = "print(isTutor)", argNames = "proceedingJoinPoint,isTutor")
    public Object authorityVerify(ProceedingJoinPoint proceedingJoinPoint, IsTutor isTutor) {

        try {
            HttpServletRequest request = HttpUtil.getRequest();
            int uid = CookieUtil.parseInt(request.getCookies(), "uid");
            TutorMessage tutorMessage = tutorService.getTutorMsg(uid);
            if (tutorMessage != null) {
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
