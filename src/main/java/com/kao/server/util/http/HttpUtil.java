package com.kao.server.util.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 全鸿润
 */
public class HttpUtil {

    private static final ServletRequestAttributes SERVLET_REQUEST_ATTRIBUTES = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    public static HttpServletRequest getRequest() throws Exception {
        if (SERVLET_REQUEST_ATTRIBUTES != null) {
            return SERVLET_REQUEST_ATTRIBUTES.getRequest();
        } else {
            System.err.println("Error");
            throw new Exception();
        }
    }

    public static HttpServletResponse getResponse() throws Exception {
        if (SERVLET_REQUEST_ATTRIBUTES != null) {
            return SERVLET_REQUEST_ATTRIBUTES.getResponse();
        } else {
            throw new Exception();
        }
    }
}
