package com.kao.server.util.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.util.json.GetRequestData;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.login.IsLoggedIn;
import com.kao.server.util.token.TokenVerifytor;
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
public class AuthorityIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没有要求实现权限验证的方法请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        IsLoggedIn anotation = method.getAnnotation(IsLoggedIn.class);
        JSONObject jsonResult = new JSONObject();
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        //需要拦截的方法
        if (anotation != null) {
            try{
                JSONObject jsonObject = JSONObject.parseObject(GetRequestData.getJsonDataFromRequest(request));
                String accessToken = jsonObject.getString("accessToken");
                if (accessToken != null) {
                    boolean isLogined = TokenVerifytor.verifyToken(accessToken);
                    System.err.println(isLogined);
                    if (isLogined){
                        jsonResult.put("state", JsonResultStatus.SUCCESS);
                    }else{
                        jsonResult.put("state",JsonResultStatus.UNAUTHORIZED_USER);
                        jsonResult.put("message","当前身份不符，请先登录！");
                    }
                    out.print(jsonResult.toString());
                    out.close();
                    return isLogined;
                } else {
                    jsonResult.put("state",JsonResultStatus.UNAUTHORIZED_USER);
                    jsonResult.put("message","当前身份不符，请先登录！");
                    out.print(jsonResult.toString());
                    out.close();
                    return false;
                }
            }catch (Exception e){
                jsonResult.put("state",JsonResultStatus.BAD_REQUEST);
                jsonResult.put("message","传递的数据格式有误!");
                out.print(jsonResult.toString());
                out.close();
                return false;
            }
        }
        jsonResult.put("state",JsonResultStatus.UNAUTHORIZED_USER);
        jsonResult.put("message","当前身份不符，请先登录！");
        out.close();
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
