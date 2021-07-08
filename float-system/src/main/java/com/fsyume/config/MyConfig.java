package com.fsyume.config;

import com.fsyume.interceptors.JwtInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptors())// 指定拦截器
                .addPathPatterns("/user/*");// 拦截路径
    }
}
