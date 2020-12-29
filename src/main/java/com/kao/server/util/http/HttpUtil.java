package com.kao.server.util.http;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author 全鸿润、沈伟峰
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

    /**
     * 封装 url 参数，格式如下：<br/>
     * key1=value1&key2=value2...
     *
     * @param map 参数列表
     * @return 封装好的 url 参数
     */
    public static String packUrlParams(Map<?, ?> map) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    URLEncoder.encode(entry.getKey().toString(), "UTF-8"),
                    URLEncoder.encode(entry.getValue().toString(), "UTF-8")
            ));
        }
        return sb.toString();
    }
}
