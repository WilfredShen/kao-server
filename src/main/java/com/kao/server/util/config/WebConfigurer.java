package com.kao.server.util.config;

import com.kao.server.util.intercepter.AuthorityInterceptor;
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
        excludes.add("/visitor/update_password");
        excludes.add("/visitor/getvfcode");
        excludes.add("/meta/*");
        excludes.add("/base/*");
        excludes.add("/admin/login");
        registry.addInterceptor(new AuthorityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludes);
    }
}
