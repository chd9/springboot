package com.chd9.manage.config;

import com.chd9.manage.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @Author: donnie99
 * @Date: 2020/4/30 23:28
 * @Version 1.0
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/*/**")
                .excludePathPatterns("/auth/**")
                .excludePathPatterns("/static/**");

    }
}
