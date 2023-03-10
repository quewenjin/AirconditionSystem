package com.example.airconditionsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                // 那些路径不拦截
                .excludePathPatterns("/user/login","/error")
//                 swagger2配置
//                .excludePathPatterns("/swagger-resources","/error")
//                .excludePathPatterns("/swagger-resources/configuration/ui","/error")
//                .excludePathPatterns("/swagger-resources/configuration/security","/error")
        ;
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }
}