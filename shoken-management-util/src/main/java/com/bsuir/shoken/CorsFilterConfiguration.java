package com.bsuir.shoken;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor(onConstructor = @__({@Autowired}))

@Configuration
public class CorsFilterConfiguration {

    public final CorsFilter corsFilter;

    @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        final FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
        bean.setOrder(0);

        return bean;
    }
}
