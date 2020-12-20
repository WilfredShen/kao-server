package com.kao.server.util.cookie;

import com.kao.server.util.properties.CookieProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;

/**
 * @author 沈伟峰
 */
@Component
public class CookieUtil {

    private static CookieUtil cookieUtil;
    @Autowired
    private CookieProperties properties;

    /**
     * 生成 Cookie
     *
     * @param key    cookie name
     * @param value  cookie 值
     * @param domain 有效域名
     * @param path   有效路径
     * @param maxAge 有效期
     * @return 生成的 Cookie
     */
    public static Cookie buildCookie(String key, String value, String domain, String path, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    /**
     * 生成 Cookie，未指定的属性使用默认值
     *
     * @param key   cookie name
     * @param value cookie 值
     * @return 生成的 Cookie
     */
    public static Cookie buildCookie(String key, String value) {
        return buildCookie(key, value, cookieUtil.properties.domain, cookieUtil.properties.path, cookieUtil.properties.maxAge);
    }

    /**
     * 从 cookie 列表中查找 name == key 的 cookie，并返回
     *
     * @param cookies cookie 列表
     * @param key     cookie name
     * @return 找到的 cookie
     */
    public static Cookie findCookie(Cookie[] cookies, String key) {
        Cookie result = null;
        try {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    result = cookie;
                    break;
                }
            }
        } catch (Exception ignored) {
        }
        return result;
    }

    /**
     * 从 cookie 列表中查找 name == key 的 cookie，并将其 value 解析为 Integer 并返回
     *
     * @param cookies cookie 列表
     * @param key     cookie name
     * @return 解析结果
     */
    public static Integer parseInt(Cookie[] cookies, String key) {
        Cookie cookie = findCookie(cookies, key);
        Integer i = null;
        if (cookie != null) {
            try {
                i = Integer.parseInt(cookie.getValue());
            } catch (Exception ignored) {
            }
        }
        return i;
    }

    /**
     * 构造函数调用后自动调用该函数，对静态变量进行初始化
     * 借此实现 Spring 框架对静态变量进行注入
     */
    @PostConstruct
    public void init() {
        cookieUtil = this;
        cookieUtil.properties = this.properties;
    }

}
