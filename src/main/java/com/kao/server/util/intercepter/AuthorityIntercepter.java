package com.kao.server.util.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.json.GetRequestData;
import com.kao.server.util.login.IsLogined;
import com.kao.server.util.token.TokenVerifytor;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author 全鸿润
 */
public class AuthorityIntercepter implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没有要求实现权限验证的方法请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        IsLogined anotation = method.getAnnotation(IsLogined.class);
        //需要拦截的方法
        if (anotation != null) {
            JSONObject jsonObject = JSONObject.parseObject(GetRequestData.getJsonDataFromRequest(request));
            String accessToken = jsonObject.getString("accessToken");
            if (accessToken != null) {
                boolean isLogined = TokenVerifytor.verifyToken(accessToken);
                return isLogined;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
