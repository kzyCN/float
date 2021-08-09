package com.fsyume.config;

import com.fsyume.interceptors.JwtAdminInterceptors;
import com.fsyume.interceptors.JwtInterceptors;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器
     *
     * @param registry 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptors())// 指定拦截器
                .addPathPatterns("/user/delete");// 拦截路径

        registry.addInterceptor(new JwtAdminInterceptors())
                .addPathPatterns("/user/findall");
    }
}
