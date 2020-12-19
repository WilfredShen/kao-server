package com.kao.server.util.config;

import com.kao.server.util.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 全鸿润
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Bean
    public HandlerInterceptor getAuthorityInterceptor() {
        return new AuthorityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludes = new ArrayList<>();
        excludes.add("/visitor/login");
        excludes.add("/visitor/register");
        excludes.add("/visitor/update-password");
        excludes.add("/visitor/getvfcode");
        excludes.add("/meta/*");
        excludes.add("/base/*");
        excludes.add("/admin/login");
        registry.addInterceptor(getAuthorityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludes);
    }
}
