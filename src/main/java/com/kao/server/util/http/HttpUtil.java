package com.kao.server.util.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 全鸿润
 */
public class HttpUtil {

    /**
     * 获取当前请求的HttpServletRequest对象
     *
     * @return 当前请求的HttpServletRequest对象
     * @throws Exception 获取异常
     */
    public static HttpServletRequest getRequest() throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        } else {
            throw new Exception();
        }
    }

    /**
     * 获取当前请求的HttpServletResponse对象
     *
     * @return 当前请求的HttpServletResponse对象
     * @throws Exception 获取异常
     */
    public static HttpServletResponse getResponse() throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getResponse();
        } else {
            throw new Exception();
        }
    }
}
