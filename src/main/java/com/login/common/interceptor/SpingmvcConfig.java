package com.login.common.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SpingmvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UserLoginInterceptorBySpring localinterceptor() {
        return new UserLoginInterceptorBySpring();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //配置 不需要拦截的包和请求
        String[]excludePathPatterns=new String[]{
                "/admin/user/login"
        };
        registry.addInterceptor(  localinterceptor()).addPathPatterns("/admin/**").excludePathPatterns(excludePathPatterns);
    }
}
