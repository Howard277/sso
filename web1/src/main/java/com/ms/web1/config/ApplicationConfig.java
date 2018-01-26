package com.ms.web1.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ms.web1.filter.AuthenticationFilter;

/**
 * 应用程序配置定义
 *
 * @author wuketao
 */
@Configuration
public class ApplicationConfig {

//    /**
//     * 配置自定义验证过滤器
//     *
//     * @return
//     */
//    @Bean
//    public AuthenticationFilter authenticationFilter() {
//        return new AuthenticationFilter();
//    }

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
        urlList.add("/user/*");
        bean.setUrlPatterns(urlList);
        return bean;
    }
}
