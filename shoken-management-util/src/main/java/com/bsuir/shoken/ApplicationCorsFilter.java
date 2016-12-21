package com.bsuir.shoken;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Component
public class ApplicationCorsFilter extends CorsFilter {

    public ApplicationCorsFilter() {
        super(configurationSource());
    }

    private static ResourceHttpRequestHandler configurationSource() {

        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");

        final ResourceHttpRequestHandler source = new ResourceHttpRequestHandler();
        source.setCorsConfiguration(corsConfiguration);

        return source;
    }
}
