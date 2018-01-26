package com.ms.cas.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.cas.filter.AuthenticationFilter;

/**
 * 应用程序配置定义
 * 
 * @author wuketao
 *
 */
@Configuration
public class ApplicationConfig {

    /**
     * 注入验证过滤器
     */
    @Autowired
    private AuthenticationFilter authenticationFilter;

    /**
     * 配置过滤器注册bean
     * 
     * @return
     */
    @Bean
    public FilterRegistrationBean authFilterRegistrationBean() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(authenticationFilter);
        // 设置验证过滤器，过滤的路径。
        List<String> urlList = new ArrayList<>();
        // 配置要拦截的url
        urlList.add("/user/*");
        urlList.add("/management/*");
        bean.setUrlPatterns(urlList);
        return bean;
    }
}
