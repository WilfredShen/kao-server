package com.kao.server.util.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 全鸿润
 */
public class HttpUtil {

    public static HttpServletRequest getRequest() throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        } else {
            throw new Exception();
        }
    }

    public static HttpServletResponse getResponse() throws Exception {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getResponse();
        } else {
            throw new Exception();
        }
    }
}
