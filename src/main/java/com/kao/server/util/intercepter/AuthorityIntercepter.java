package com.kao.server.util.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.kao.server.service.impl.UserServiceImpl;
import com.kao.server.util.cookie.CookieUtil;
import com.kao.server.util.json.JsonResultStatus;
import com.kao.server.util.token.TokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @author 全鸿润
 */
public class AuthorityIntercepter implements HandlerInterceptor {

    @Autowired
    UserServiceImpl userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //没有要求实现权限验证的方法请求
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        IsLoggedIn annotation = method.getAnnotation(IsLoggedIn.class);

        HttpSession session = request.getSession();
        JSONObject jsonResult = new JSONObject();
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");


        //需要拦截的方法
        if (annotation != null) {
            try {
                String accessToken = CookieUtil.findCookie(request.getCookies(), "accessToken").getValue();
                if (accessToken != null) {
                    boolean isLogin = TokenVerifier.verifyToken(accessToken);
                    //先判断登录
                    if (isLogin) {
                        String username = TokenVerifier.getUserNameFromToken(accessToken);
                        String password = TokenVerifier.getPasswordFromToken(accessToken);
                        String accountType = TokenVerifier.getAccountTypeFromToken(accessToken);
                        //判断token是否属于当前用户
                        if (!session.getAttribute("username").equals(username) || !session.getAttribute("password").equals(password)) {

                            PrintWriter out = response.getWriter();
                            jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                            jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                            out.print(jsonResult.toString());
                            out.close();
                            return false;
                        } else {
                            //判断权限
                            if (method.getAnnotation(IsStudent.class) != null && !AccountTypeConstant.getStudentType().equals(accountType)) {
                                PrintWriter out = response.getWriter();
                                jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                                jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                                out.print(jsonResult.toString());
                                out.close();
                                return false;
                            }
                            if (method.getAnnotation(IsTutor.class) != null && !AccountTypeConstant.getTeacherType().equals(accountType)) {
                                PrintWriter out = response.getWriter();
                                jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                                jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                                out.print(jsonResult.toString());
                                out.close();
                                return false;
                            }
                            if (method.getAnnotation(IsAdmin.class) != null && !AccountTypeConstant.getAdminType().equals(accountType)) {
                                PrintWriter out = response.getWriter();
                                jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                                jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                                out.print(jsonResult.toString());
                                out.close();
                                return false;
                            }
                            return true;
                        }
                    } else {
                        PrintWriter out = response.getWriter();
                        jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                        jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                        out.close();
                        out.print(jsonResult.toString());
                    }
                    return false;
                } else {
                    PrintWriter out = response.getWriter();
                    jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                    jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                    out.close();
                    out.print(jsonResult.toString());
                    return false;
                }
            } catch (Exception e) {
                System.err.println("token格式不对或没有token");
                PrintWriter out = response.getWriter();
                jsonResult.put("state", JsonResultStatus.UNAUTHORIZED);
                jsonResult.put("message", JsonResultStatus.UNAUTHORIZED_DESC);
                out.print(jsonResult.toString());
                out.close();
                return false;
            }
        }
        PrintWriter out = response.getWriter();
        jsonResult.put("state", JsonResultStatus.ILLEGAL_PARAM);
        jsonResult.put("message", JsonResultStatus.ILLEGAL_PARAM_DESC);
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
