package com.kao.server.util.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 沈伟峰
 */
@Component
@PropertySource(value = {"classpath:application-cookie.yml"})
public class CookieProperties {

    @Value("${cookie.domain}")
    public String domain;
    @Value("${cookie.path}")
    public String path;
    @Value("${cookie.maxAge}")
    public int maxAge;
}
