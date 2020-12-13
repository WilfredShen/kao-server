package com.kao.server.util.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.AdminService;
import com.kao.server.service.UserService;
import com.kao.server.util.checker.AdminChecker;
import com.kao.server.util.checker.UserChecker;
import com.kao.server.util.json.JsonResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author 全鸿润
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没有要求实现权限验证的方法请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        JSONObject jsonResult = new JSONObject();
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        //需要拦截的方法
        try {
            boolean flag;
            if (method.getAnnotation(IsLoggedIn.class) != null) {
                if (method.getAnnotation(IsAdmin.class) != null) {
                    flag = AdminChecker.check(adminService);
                } else if (method.getAnnotation(IsStudent.class) != null || method.getAnnotation(IsTutor.class) != null) {
                    flag = UserChecker.check(method, userService);
                } else {
                    flag = true;
                }
                if (!flag) {
                    PrintWriter out = response.getWriter();
                    jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                    jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                    out.print(jsonResult.toString());
                    out.close();
                }
                System.err.println("验证成功！");
                return flag;
            } else {
                PrintWriter out = response.getWriter();
                jsonResult.put("state", JsonResultStatus.ILLEGAL_PARAM);
                jsonResult.put("message", JsonResultStatus.ILLEGAL_PARAM_DESC);
                out.print(jsonResult.toString());
                out.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            jsonResult.put("state", JsonResultStatus.UNKNOWN_ERROR);
            jsonResult.put("message", JsonResultStatus.UNKNOWN_ERROR_DESC);
            out.print(jsonResult.toString());
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
