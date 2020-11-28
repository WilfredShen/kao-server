package com.kao.server.util.cookie;

import javax.servlet.http.Cookie;

/**
 * @author 沈伟峰
 */
public class CookieUtil {

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
}
