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

    public static Cookie buildCookie(String key, String value, String domain, String path, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setDomain(domain);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    public static Cookie buildCookie(String key, String value) {
        return buildCookie(key, value, cookieUtil.properties.domain, cookieUtil.properties.path, cookieUtil.properties.maxAge);
    }

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

    @PostConstruct
    public void init() {
        cookieUtil = this;
        cookieUtil.properties = this.properties;
    }

}
