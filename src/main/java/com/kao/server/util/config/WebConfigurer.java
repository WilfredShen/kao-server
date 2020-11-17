package com.kao.server.util.config;

import com.kao.server.util.intercepter.AuthorityIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 全鸿润
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorityIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns("/visitor/login", "/visitor/register","/visitor/getvfcode");
    }
}
