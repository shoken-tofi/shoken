package com.bsuir.shoken;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        return new ApplicationCorsFilter();
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        final FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter());
        bean.setOrder(0);

        return bean;
    }
}
