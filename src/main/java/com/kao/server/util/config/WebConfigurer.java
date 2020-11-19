package com.kao.server.util.config;

import com.kao.server.util.intercepter.AuthorityIntercepter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 全鸿润
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludes = new ArrayList<>();
        excludes.add("/visitor/login");
        excludes.add("/visitor/register");
        excludes.add("/visitor/getvfcode");
        excludes.add("/meta/*");
        excludes.add("/base/*");
        registry.addInterceptor(new AuthorityIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns(excludes);
    }
}
